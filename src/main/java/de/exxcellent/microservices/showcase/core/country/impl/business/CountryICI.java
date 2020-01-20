package de.exxcellent.microservices.showcase.core.country.impl.business;

import de.exxcellent.microservices.showcase.core.country.impl.persistence.model.CountryET;

import java.util.Set;

/**
 * The inner-component interface (ICI) of the country component.
 *
 * @author Felix Riess
 * @since 20.01.20
 */
public interface CountryICI {
    /**
     * Get all countries.
     *
     * @return a {@link Set} containing all countries as {@link CountryET}.
     */
    Set<CountryET> getCountries();

    /**
     * Get the country with the provided short name.
     *
     * @param shortName the short name (ID) of the country to be returned. (not {@code null}).
     * @return the country with the provided short name as {@link CountryET}.
     * @exception de.exxcellent.microservices.showcase.common.errorhandling.exception.BusinessException with {@link de.exxcellent.microservices.showcase.common.errorhandling.ErrorCode#NOT_FOUND_ERROR} if no country with the provided short name is existing.
     */
    CountryET getCountry(final String shortName);

    /**
     * Adds a new country. Does not add duplicates.
     *
     * @param country the country to be added as {@link CountryET}. (must be valid, see {@link de.exxcellent.microservices.showcase.core.country.impl.access.CountryValidation#validateCountryET(CountryET)}).
     * @return all countries including the new one as {@link Set} of {@link CountryET}s.
     */
    Set<CountryET> addCountry(final CountryET country);
}
