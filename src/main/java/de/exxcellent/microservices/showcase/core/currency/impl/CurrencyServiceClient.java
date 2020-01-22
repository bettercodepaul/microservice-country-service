package de.exxcellent.microservices.showcase.core.currency.impl;

import de.exxcellent.microservices.showcase.common.errorhandling.ErrorCode;
import de.exxcellent.microservices.showcase.common.errorhandling.exception.TechnicalException;
import de.exxcellent.microservices.showcase.common.validation.Preconditions;
import de.exxcellent.microservices.showcase.core.currency.api.CurrencyESI;
import de.exxcellent.microservices.showcase.core.currency.api.types.CountryWithCurrencyCTO;
import org.eclipse.microprofile.rest.client.inject.RestClient;

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

    @Inject
    @RestClient
    private CurrencyServiceInterface currencyService;

    @Override
    public Set<CountryWithCurrencyCTO> getCountriesWithCurrency() {
        final Response response = this.currencyService.getCountriesWithCurrency();
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
        final Response response = this.currencyService.getCountryWithCurrency(countryShortName);
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
        final Response response = this.currencyService.createCountryWithCurrency(countryWithCurrency);
        if(response.getStatus() == 200) {
            return new HashSet<>(Arrays.asList(response.readEntity(CountryWithCurrencyCTO[].class)));
        } else {
            // 4xx and 5xx responses are forwarded by the framework, so we should never run in this else block.
            throw new TechnicalException(ErrorCode.INTERNAL_ERROR, "Application reached an unstable state at: CurrencyServiceClient.CreateCountryWithCurrency. Please contact 3rd level support");
        }
    }

}
