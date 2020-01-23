package de.exxcellent.microservices.showcase.core.country.impl.access;

import de.exxcellent.microservices.showcase.common.validation.Preconditions;
import de.exxcellent.microservices.showcase.core.country.api.CountryBCI;
import de.exxcellent.microservices.showcase.core.country.api.types.CountryTO;
import de.exxcellent.microservices.showcase.core.country.api.types.CountryWithLanguageAndCurrencyCTO;
import de.exxcellent.microservices.showcase.core.country.impl.business.CountryICI;
import de.exxcellent.microservices.showcase.core.country.impl.persistence.model.CountryET;
import de.exxcellent.microservices.showcase.core.currency.api.CurrencyESI;
import de.exxcellent.microservices.showcase.core.currency.api.types.CountryWithCurrencyCTO;
import de.exxcellent.microservices.showcase.core.currency.api.types.CurrencyTO;
import de.exxcellent.microservices.showcase.core.language.api.LanguageESI;
import de.exxcellent.microservices.showcase.core.language.api.types.CountryWithLanguageCTO;
import de.exxcellent.microservices.showcase.core.language.api.types.LanguageTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * The business facade (BF) of the country component. Implements the {@link CountryBCI}.
 *
 * @author Felix Riess
 * @since 20.01.20
 */
@ApplicationScoped
public class CountryBF implements CountryBCI {
    /**
     * the {@link Logger} of this {@link CountryBF}.
     */
    private static final Logger LOG = LoggerFactory.getLogger(CountryBF.class);

    private final CountryICI countryManager;
    private final LanguageESI languageService;
    private final CurrencyESI currencyService;

    @Inject
    CountryBF(final CountryICI countryManager, final LanguageESI languageService, final CurrencyESI currencyService) {
        this.countryManager = countryManager;
        this.languageService = languageService;
        this.currencyService = currencyService;
    }

    @Override
    public Set<CountryTO> getCountries() {
        return this.countryManager.getCountries().stream().map(CountryMapper::toTO).collect(Collectors.toSet());
    }

    @Override
    public CountryTO getCountry(final String shortName) {
        Preconditions.checkNotNull(shortName, "Country short name must not be null");
        Preconditions.checkStringLength(shortName, 3, "Country short name must have 3 characters");
        return CountryMapper.toTO(this.countryManager.getCountry(shortName));
    }

    @Override
    public Set<CountryTO> addCountry(final CountryTO country) {
        CountryValidation.validateCountryTO(country);
        final CountryET mappedCountryET = CountryMapper.fromTO(country);
        return this.countryManager.addCountry(mappedCountryET).stream().map(CountryMapper::toTO).collect(Collectors.toSet());
    }

    @Override
    public Set<CountryWithLanguageAndCurrencyCTO> getCountriesWithLanguageAndCurrency() {
        // at first get all available countries
        final Set<CountryTO> countries = getCountries();
        // if no countries are available, return empty set and do not call other services for performance reasons
        if(countries.isEmpty()) {
            return Collections.emptySet();
        } // else: get available languages with their country from language service
        final Map<String, LanguageTO> countriesWithLanguages = this.languageService.getCountriesWithLanguage().stream()
                                                                                   .collect(Collectors.toMap(CountryWithLanguageCTO::getCountryShortName, CountryWithLanguageCTO::getLanguage));
        final long joinCountryAndLanguageStartTime = System.currentTimeMillis();
        final Map<CountryTO, LanguageTO> countryWithLanguageMap = new HashMap<>();
        countries.forEach(c -> {
            final LanguageTO matchingLanguage = countriesWithLanguages.get(c.getShortName());
            if(matchingLanguage != null) {
                countryWithLanguageMap.putIfAbsent(c, matchingLanguage);
            } // else: country has no matching language. No need to return it.
        });
        final long joinCountryAndLanguageDuration = System.currentTimeMillis() - joinCountryAndLanguageStartTime;
        LOG.info("Joining countries with languages took {} ms", joinCountryAndLanguageDuration);
        // if no countries with matching languages are available, return empty set and to not call currency service for performance reasons.
        if(countryWithLanguageMap.isEmpty()) {
            return Collections.emptySet();
        } // else: get available currencies with their country from language service
        final Map<String, CurrencyTO> countriesWithCurrencies = this.currencyService.getCountriesWithCurrency().stream()
                                                                                    .collect(Collectors.toMap(CountryWithCurrencyCTO::getCountryShortName, CountryWithCurrencyCTO::getCurrency));
        final long joinCountriesAndCurrencyStartTime = System.currentTimeMillis();
        final Set<CountryWithLanguageAndCurrencyCTO> countriesWithLanguageAndCurrency = new HashSet<>();
        countryWithLanguageMap.forEach((c, l) -> {
            final CurrencyTO matchingCurrency = countriesWithCurrencies.get(c.getShortName());
            if(matchingCurrency != null) {
                countriesWithLanguageAndCurrency.add(new CountryWithLanguageAndCurrencyCTO(c, l, matchingCurrency));
            } // else: country has no matching currency. No need to return it.
        });
        final long joinCountryAndCurrencyDuration = System.currentTimeMillis() - joinCountriesAndCurrencyStartTime;
        LOG.info("Joining countries with currencies took {} ms", joinCountryAndCurrencyDuration);
        return countriesWithLanguageAndCurrency;
    }

    @Override
    public void addCountryWithLanguageAndCurrency(final CountryWithLanguageAndCurrencyCTO countryWithLanguageAndCurrency) {
        Preconditions.checkNotNull(countryWithLanguageAndCurrency, "Country with language and currency must not be null");
        CountryValidation.validateCountryTO(countryWithLanguageAndCurrency.getCountry());
        Preconditions.checkNotNull(countryWithLanguageAndCurrency.getLanguage(), "Language must not be null");
        Preconditions.checkNotNull(countryWithLanguageAndCurrency.getLanguage().getShortName(), "Language short name must not be null");
        Preconditions.checkNotNull(countryWithLanguageAndCurrency.getLanguage().getName(), "Language name must not be null");
        Preconditions.checkStringLength(countryWithLanguageAndCurrency.getLanguage().getShortName(), 3, "Language short name must have 3 characters");
        Preconditions.checkNotNull(countryWithLanguageAndCurrency.getCurrency(), "Currency must not be null");
        Preconditions.checkNotNull(countryWithLanguageAndCurrency.getCurrency().getShortName(), "Currency short name must not be null");
        Preconditions.checkStringLength(countryWithLanguageAndCurrency.getCurrency().getShortName(), 3, "Currency short name must have 3 characters");
        Preconditions.checkNotNull(countryWithLanguageAndCurrency.getCurrency().getName(), "Currency name must not be null");

        addCountry(countryWithLanguageAndCurrency.getCountry());
        this.languageService.createCountryWithLanguage(new CountryWithLanguageCTO(countryWithLanguageAndCurrency.getCountry().getShortName(), countryWithLanguageAndCurrency.getLanguage()));
        this.currencyService.createCountryWithCurrency(new CountryWithCurrencyCTO(countryWithLanguageAndCurrency.getCountry().getShortName(), countryWithLanguageAndCurrency.getCurrency()));
    }
}
