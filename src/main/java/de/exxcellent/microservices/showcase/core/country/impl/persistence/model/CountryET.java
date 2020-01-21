package de.exxcellent.microservices.showcase.core.country.impl.persistence.model;

import de.exxcellent.microservices.showcase.common.validation.Preconditions;

import java.io.Serializable;
import java.util.Objects;

/**
 * The entity type (ET) representing a country.
 *
 * @author Felix Riess
 * @since 20.01.20
 */
public class CountryET implements Serializable {
    /**
     * generated serialVersionUID
     */
    private static final long serialVersionUID = -189837735120827056L;
    /**
     * the name of this {@link CountryET}.
     */
    private final String name;
    /**
     * the short name (ID) of this {@link CountryET}. (3 characters).
     */
    private final String shortName;

    /**
     * Constructor.
     *
     * @param name name of the country. (not {@code null}).
     * @param shortName the short name (ID) of the country (3 characters long, not {@code null}).
     */
    public CountryET(final String name, final String shortName) {
        Preconditions.checkNotNull(name, "Country name must not be null");
        Preconditions.checkNotNull(shortName, "Country short name must not be null");
        Preconditions.checkStringLength(shortName, 3, "Country short name must have 3 characters");
        this.name = name;
        this.shortName = shortName;
    }

    /**
     * Get name
     *
     * @return value of name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Get shortName
     *
     * @return value of shortName
     */
    public String getShortName() {
        return this.shortName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CountryET other = (CountryET) o;
        return Objects.equals(this.name, other.getName()) &&
                Objects.equals(this.shortName, other.getShortName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name, this.shortName);
    }

    @Override
    public String toString() {
        return "CountryET{" +
                "name='" + this.name + '\'' +
                ", shortName='" + this.shortName + '\'' +
                '}';
    }
}
