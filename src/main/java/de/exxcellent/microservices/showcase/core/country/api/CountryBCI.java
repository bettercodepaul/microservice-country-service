package de.exxcellent.microservices.showcase.core.country.api;

import de.exxcellent.microservices.showcase.core.country.api.types.CountryTO;

import java.util.Set;

/**
 * The business component interface (BCI) for the country component.
 *
 * @author Felix Riess
 * @since 20.01.20
 */
public interface CountryBCI {
    /**
     * Get all countries.
     *
     * @return a {@link Set} containing all countries as {@link CountryTO}.
     */
    Set<CountryTO> getCountries();

    /**
     * Get a country by its short name (ID).
     *
     * @param shortName the short name of the country to be returned (3 characters, not {@code null}).
     * @return the country with the provided short name as {@link CountryTO}.
     * @exception de.exxcellent.microservices.showcase.common.errorhandling.exception.BusinessException with {@link de.exxcellent.microservices.showcase.common.errorhandling.ErrorCode#NOT_FOUND_ERROR} if no country with the provided short name is existing.
     */
    CountryTO getCountry(final String shortName);

    /**
     * Add a country.
     * Does not generate duplicates.
     *
     * @param country the country to be added as {@link CountryTO}. (must be valid, see {@link de.exxcellent.microservices.showcase.core.country.impl.access.CountryValidation#validateCountryTO(CountryTO)}).
     * @return all countries including the added country as {@link Set} of {@link CountryTO}s.
     */
    Set<CountryTO> addCountry(final CountryTO country);
}
