package de.exxcellent.microservices.showcase.core.currency.api;

import de.exxcellent.microservices.showcase.core.currency.api.types.CountryWithCurrencyCTO;

import java.util.Set;

/**
 * The external service interface (ESI) for the currency service.
 *
 * @author Felix Riess, eXXcellent solutions consulting & software gmbh
 * @since 21.01.2020
 */
public interface CurrencyESI {
    /**
     * Get all countries with their currencies.
     *
     * @return all countries with their currency as {@link Set} of {@link CountryWithCurrencyCTO}.
     */
    Set<CountryWithCurrencyCTO> getCountriesWithCurrency();

    /**
     * Get the currency of the country with the provided short name.
     *
     * @param countryShortName the short name of the country of which the currency should be returned (3 characters, not {@code null}).
     * @return the country and its currency as {@link CountryWithCurrencyCTO}.
     */
    CountryWithCurrencyCTO getCountryWithCurrency(final String countryShortName);

    /**
     * Specify the currency for a country.
     *
     * @param countryWithCurrency the mapping of a country and a currency as {@link CountryWithCurrencyCTO}.
     * @return all countries with their currency as {@link Set} of {@link CountryWithCurrencyCTO}.
     */
    Set<CountryWithCurrencyCTO> createCountryWithCurrency(final CountryWithCurrencyCTO countryWithCurrency);
}
