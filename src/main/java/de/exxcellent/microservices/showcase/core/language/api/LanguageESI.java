package de.exxcellent.microservices.showcase.core.language.api;

import de.exxcellent.microservices.showcase.core.language.api.types.CountryWithLanguageCTO;

import java.util.Set;

/**
 * External system interface (ESI) to communicate with language service.
 *
 * @author Felix Riess, eXXcellent solutions consulting & software gmbh
 * @since 22.01.2020
 */
public interface LanguageESI {
    /**
     * Get all countries with their languages.
     *
     * @return all countries with their language as {@link Set} of {@link CountryWithLanguageCTO}.
     */
    Set<CountryWithLanguageCTO> getCountriesWithLanguage();

    /**
     * Get the language of the country with the provided short name.
     *
     * @param countryShortName the short name of the country of which the language should be returned (3 characters, not {@code null}).
     * @return the country and its language as {@link CountryWithLanguageCTO}.
     */
    CountryWithLanguageCTO getCountryWithLanguage(final String countryShortName);

    /**
     * Specify the Language for a country.
     *
     * @param countryWithLanguage the mapping of a country and a language as {@link CountryWithLanguageCTO}.
     * @return all countries with their language as {@link Set} of {@link CountryWithLanguageCTO}.
     */
    Set<CountryWithLanguageCTO> createCountryWithLanguage(final CountryWithLanguageCTO countryWithLanguage);
}
