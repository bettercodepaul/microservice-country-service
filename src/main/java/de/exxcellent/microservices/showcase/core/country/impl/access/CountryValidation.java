package de.exxcellent.microservices.showcase.core.country.impl.access;

import de.exxcellent.microservices.showcase.common.errorhandling.ErrorCode;
import de.exxcellent.microservices.showcase.common.errorhandling.exception.TechnicalException;
import de.exxcellent.microservices.showcase.common.validation.Preconditions;
import de.exxcellent.microservices.showcase.core.country.api.types.CountryTO;
import de.exxcellent.microservices.showcase.core.country.impl.persistence.model.CountryET;

/**
 * A simple helper class to validate {@link CountryTO}s and {@link CountryET}s.
 *
 * @author Felix Riess
 * @since 20.01.20
 */
public final class CountryValidation {

    private static final String COUNTRY_NOT_NULL = "Country must not be null";
    private static final String COUNTRY_NAME_NOT_NULL = "Country name must not be null";
    private static final String COUNTRY_SHORT_NAME_NOT_NULL = "Country short name must not be null";
    private static final String COUNTRY_SHORT_NAME_LENGTH = "Country short name must have 3 characters";

    /**
     * private constructor to hide implicit public one.
     * @exception TechnicalException if class is tried to be instantiated.
     */
    private CountryValidation() {
        throw new TechnicalException(ErrorCode.ILLEGAL_ACCESS_ERROR, "CountryValidation is a utility class with static methods and must not be instantiated");
    }

    /**
     * Validates a {@link CountryTO}.
     *
     * The following must not be {@code null}:
     * <ol>
     *     <li>{@link CountryTO}</li>
     *     <li>{@link CountryTO#getName()}</li>
     *     <li>{@link CountryTO#getShortName()}</li>
     * </ol>
     *
     * @param country the country to be validated as {@link CountryTO}.
     */
    public static void validateCountryTO(final CountryTO country) {
        Preconditions.checkNotNull(country, COUNTRY_NOT_NULL);
        Preconditions.checkNotNull(country.getName(), COUNTRY_NAME_NOT_NULL);
        Preconditions.checkNotNull(country.getShortName(), COUNTRY_SHORT_NAME_NOT_NULL);
        Preconditions.checkStringLength(country.getShortName(), 3, COUNTRY_SHORT_NAME_LENGTH);
    }

    /**
     * Validates a {@link CountryET}.
     *
     * The following must not be {@code null}:
     * <ol>
     *     <li>{@link CountryET}</li>
     *     <li>{@link CountryET#getName()}</li>
     *     <li>{@link CountryET#getShortName()}</li>
     * </ol>
     *
     * @param country the country to be validated as {@link CountryET}.
     */
    public static void validateCountryET(final CountryET country) {
        Preconditions.checkNotNull(country, COUNTRY_NOT_NULL);
        Preconditions.checkNotNull(country.getName(), COUNTRY_NAME_NOT_NULL);
        Preconditions.checkNotNull(country.getShortName(), COUNTRY_SHORT_NAME_NOT_NULL);
        Preconditions.checkStringLength(country.getShortName(), 3, COUNTRY_SHORT_NAME_LENGTH);
    }
}
