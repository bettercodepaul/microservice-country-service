package de.exxcellent.microservices.showcase.webservice.api.v1.country;

import de.exxcellent.microservices.showcase.common.errorhandling.ErrorCode;
import de.exxcellent.microservices.showcase.common.errorhandling.exception.BusinessException;
import de.exxcellent.microservices.showcase.common.validation.Preconditions;
import de.exxcellent.microservices.showcase.core.country.api.CountryBCI;
import de.exxcellent.microservices.showcase.core.country.api.types.CountryWithLanguageAndCurrencyCTO;
import de.exxcellent.microservices.showcase.core.country.impl.access.CountryValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Set;

/**
 * REST API to provide information about countries with their language and currency.
 *
 * @author Felix Riess, eXXcellent solutions consulting & software gmbh
 * @since 23.01.2020
 */
@Path("/api/v1/countries-with-languages-and-currencies")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CountryWithLanguageAndCurrencyFacade {
    /**
     * The {@link Logger} of this {@link CountryWithLanguageAndCurrencyFacade}.
     */
    private static final Logger LOG = LoggerFactory.getLogger(CountryWithLanguageAndCurrencyFacade.class);

    private final CountryBCI countryService;

    @Inject
    CountryWithLanguageAndCurrencyFacade(final CountryBCI countryService) {
        this.countryService = countryService;
    }

    /**
     * Get all countries with their matching language and currency.
     *
     * @return a {@link Set} of the countries which have a matching language and currency.
     * @exception BusinessException with {@link ErrorCode#EMPTY_LIST_ERROR} if no country has a matching language and currency.
     */
    @GET
    public Set<CountryWithLanguageAndCurrencyCTO> getCountriesWithLanguagesAndCurrency() {
        LOG.info("Resource to get countries with their languages and currency triggered");
        final Set<CountryWithLanguageAndCurrencyCTO> countriesWithLanguagesAndCurrencies = this.countryService.getCountriesWithLanguageAndCurrency();
        if(countriesWithLanguagesAndCurrencies.isEmpty()){
            throw new BusinessException(ErrorCode.EMPTY_LIST_ERROR, "No countries with matching languages and currencies available");
        } else {
            return countriesWithLanguagesAndCurrencies;
        }
    }

    /**
     * Adds the provided mapping of country, language and currency.
     *
     * @param countryWithLanguageAndCurrency the country, language and currency mapping as {@link CountryWithLanguageAndCurrencyCTO}.
     */
    @POST
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
        LOG.info("Resource to add country {} with language {} and currency {} triggered", countryWithLanguageAndCurrency.getCountry().getName(), countryWithLanguageAndCurrency.getLanguage().getName(), countryWithLanguageAndCurrency.getCurrency().getName());
        this.countryService.addCountryWithLanguageAndCurrency(countryWithLanguageAndCurrency);
    }
}
