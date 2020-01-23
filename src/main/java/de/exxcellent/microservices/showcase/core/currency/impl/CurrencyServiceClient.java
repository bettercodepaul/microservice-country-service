package de.exxcellent.microservices.showcase.core.currency.impl;

import de.exxcellent.microservices.showcase.common.errorhandling.ErrorCode;
import de.exxcellent.microservices.showcase.common.errorhandling.exception.TechnicalException;
import de.exxcellent.microservices.showcase.common.validation.Preconditions;
import de.exxcellent.microservices.showcase.core.currency.api.CurrencyESI;
import de.exxcellent.microservices.showcase.core.currency.api.types.CountryWithCurrencyCTO;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * A client to communicate with the currency service application. Implementation of {@link CurrencyESI}.
 *
 * @author Felix Riess, eXXcellent solutions consulting & software gmbh
 * @since 21.01.2020
 */
@ApplicationScoped
public class CurrencyServiceClient implements CurrencyESI {
    /**
     * The {@link Logger} of this {@link CurrencyServiceClient}.
     */
    private static final Logger LOG = LoggerFactory.getLogger(CurrencyServiceClient.class);

    @Inject
    @RestClient
    private CurrencyServiceInterface currencyService;

    @Override
    public Set<CountryWithCurrencyCTO> getCountriesWithCurrency() {
        LOG.info("Calling currency service to get all countries with their currency");
        final long startTime = System.currentTimeMillis();
        final Response response = this.currencyService.getCountriesWithCurrency();
        final long durationInMillis = System.currentTimeMillis() - startTime;
        LOG.info("Call to currency service to get all countries with their currency took {} ms", durationInMillis);
        if(response.getStatus() == 200) {
            return new HashSet<>(Arrays.asList(response.readEntity(CountryWithCurrencyCTO[].class)));
        } else if (response.getStatus() == 204){
            return Collections.emptySet();
        } else {
            // 4xx and 5xx responses are forwarded by the framework, so we should never run in this else block.
            throw new TechnicalException(ErrorCode.INTERNAL_ERROR, "Application reached an unstable state at: CurrencyServiceClient.GetCountriesWithCurrency. Please contact 3rd level support");
        }
    }

    @Override
    public CountryWithCurrencyCTO getCountryWithCurrency(final String countryShortName) {
        Preconditions.checkNotNull(countryShortName, "Country short name must not be null");
        Preconditions.checkStringLength(countryShortName, 3, "Country short name must have 3 characters");
        LOG.info("Calling currency service to get currency of country with short name {}", countryShortName);
        final long startTime = System.currentTimeMillis();
        final Response response = this.currencyService.getCountryWithCurrency(countryShortName);
        final long durationInMillis = System.currentTimeMillis() - startTime;
        LOG.info("Call to currency service to get currency of country {} took {} ms", countryShortName, durationInMillis);
        if(response.getStatus() == 200) {
            return response.readEntity(CountryWithCurrencyCTO.class);
        } else {
            // 4xx and 5xx responses are forwarded by the framework, so we should never run in this else block.
            throw new TechnicalException(ErrorCode.INTERNAL_ERROR, "Application reached an unstable state at: CurrencyServiceClient.GetCountryWithCurrency. Please contact 3rd level support");
        }
    }

    @Override
    public Set<CountryWithCurrencyCTO> createCountryWithCurrency(final CountryWithCurrencyCTO countryWithCurrency) {
        Preconditions.checkNotNull(countryWithCurrency, "Country with currency must not be null");
        Preconditions.checkNotNull(countryWithCurrency.getCountryShortName(), "Country short name must not be null");
        Preconditions.checkStringLength(countryWithCurrency.getCountryShortName(), 3, "Country short name must have 3 characters");
        Preconditions.checkNotNull(countryWithCurrency.getCurrency(), "Currency must not be null");
        Preconditions.checkNotNull(countryWithCurrency.getCurrency().getShortName(), "Currency short name must not be null");
        Preconditions.checkStringLength(countryWithCurrency.getCurrency().getShortName(), 3, "Currency short name must have 3 characters");
        Preconditions.checkNotNull(countryWithCurrency.getCurrency().getName(), "Currency name must not be null");
        LOG.info("Calling currency service to create mapping of country {} with currency {}", countryWithCurrency.getCountryShortName(), countryWithCurrency.getCurrency().getName());
        final long startTime = System.currentTimeMillis();
        try(final Response response = this.currencyService.createCountryWithCurrency(countryWithCurrency)) {
            final long durationInMillis = System.currentTimeMillis() - startTime;
            LOG.info("Call to currency service to create mapping of country {} with currency {} took {} ms", countryWithCurrency.getCountryShortName(), countryWithCurrency.getCurrency().getName(), durationInMillis);
            if(response.getStatus() == 200) {
                return new HashSet<>(Arrays.asList(response.readEntity(CountryWithCurrencyCTO[].class)));
            } else {
                // 4xx and 5xx responses are forwarded by the framework, so we should never run in this else block.
                throw new TechnicalException(ErrorCode.INTERNAL_ERROR, "Application reached an unstable state at: CurrencyServiceClient.CreateCountryWithCurrency. Please contact 3rd level support");
            }
        }
    }

}
