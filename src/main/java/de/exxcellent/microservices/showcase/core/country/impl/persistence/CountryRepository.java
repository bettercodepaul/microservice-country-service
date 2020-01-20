package de.exxcellent.microservices.showcase.core.country.impl.persistence;

import de.exxcellent.microservices.showcase.core.country.impl.persistence.model.CountryET;

import java.util.Set;

/**
 * The interface for all storage operations.
 *
 * @author Felix Riess
 * @since 20.01.20
 */
public interface CountryRepository {
    /**
     * Get all available countries.
     *
     * @return all countries as {@link Set} of {@link CountryET}s.
     */
    Set<CountryET> getCountries();

    /**
     * Get a country by its short name (ID).
     *
     * @param shortName the short name (ID) of the country to be returned (not {@code null}).
     * @return the country with the provided short name as {@link CountryET}.
     * @exception de.exxcellent.microservices.showcase.common.errorhandling.exception.BusinessException with {@link de.exxcellent.microservices.showcase.common.errorhandling.ErrorCode#NOT_FOUND_ERROR} if no country with the provided short name is existing.
     */
    CountryET getCountry(final String shortName);

    /**
     * Adds the given country to the known countries.
     * Does not add duplicates.
     *
     * @param country the country to be added as {@link CountryET}.
     * @return all countries including the newly added country as {@link Set} of {@link CountryET}s.
     */
    Set<CountryET> addCountry(final CountryET country);
}
