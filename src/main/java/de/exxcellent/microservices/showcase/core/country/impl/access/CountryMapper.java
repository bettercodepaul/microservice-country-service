package de.exxcellent.microservices.showcase.core.country.impl.access;

import de.exxcellent.microservices.showcase.common.errorhandling.ErrorCode;
import de.exxcellent.microservices.showcase.common.errorhandling.exception.TechnicalException;
import de.exxcellent.microservices.showcase.core.country.api.types.CountryTO;
import de.exxcellent.microservices.showcase.core.country.impl.persistence.model.CountryET;

/**
 * Maps a {@link CountryET} to a {@link CountryTO} and vice versa.
 *
 * @author Felix Riess
 * @since 20.01.20
 */
public final class CountryMapper {
    /**
     * private constructor to hide implicit public one.
     * @exception TechnicalException if class is tried to be instantiated.
     */
    private CountryMapper() {
        throw new TechnicalException(ErrorCode.ILLEGAL_ACCESS_ERROR, "CountryMapper is a utility class with static methods and must not be instantiated");
    }

    /**
     * Map a {@link CountryET} to a {@link CountryTO}.
     *
     * @param country the {@link CountryET} to be mapped. (must be valid, see {@link CountryValidation#validateCountryET(CountryET)}).
     * @return the {@link CountryTO} containing the information from the {@link CountryET}.
     */
    public static CountryTO toTO(final CountryET country) {
        CountryValidation.validateCountryET(country);
        return new CountryTO(country.getName(), country.getShortName());
    }

    /**
     * Map a {@link CountryTO} to a {@link CountryET}.
     *
     * @param country the {@link CountryTO} to be mapped. (must be valid, see {@link CountryValidation#validateCountryTO(CountryTO)}).
     * @return the {@link CountryET} containing the information from the {@link CountryTO}.
     */
    public static CountryET fromTO(final CountryTO country) {
        CountryValidation.validateCountryTO(country);
        return new CountryET(country.getName(), country.getShortName());
    }
}
