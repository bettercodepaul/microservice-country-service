package de.exxcellent.microservices.showcase.core.country.api.types;

import de.exxcellent.microservices.showcase.common.validation.Preconditions;

import java.io.Serializable;

/**
 * The transport object holding the information about a country.
 *
 * @author Felix Riess
 * @since 20.01.20
 */
public class CountryTO implements Serializable {

    /**
     * generated serialVersionUID
     */
    private static final long serialVersionUID = 7770035607272815276L;
    /**
     * the name of the country.
     */
    private String name;
    /**
     * the short name (ID) of the country. (exact 3 characters).
     */
    private String shortName;

    /**
     * empty constructor for JSON parsing.
     */
    public CountryTO() {

    }

    /**
     * Constructor.
     *
     * @param name the name of the country (not {@code null}).
     * @param shortName the short name (ID) of the country (not {@code null}).
     */
    public CountryTO(final String name, final String shortName) {
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
     * Set the name
     *
     * @param name the name to set (not {@code null})
     */
    public void setName(final String name) {
        Preconditions.checkNotNull(name, "Country name must not be null");
        this.name = name;
    }

    /**
     * Get shortName
     *
     * @return value of shortName
     */
    public String getShortName() {
        return this.shortName;
    }

    /**
     * Set the shortName
     *
     * @param shortName the shortName to set (not {@code null}).
     */
    public void setShortName(final String shortName) {
        Preconditions.checkNotNull(shortName, "Country short name must not be null");
        Preconditions.checkStringLength(shortName, 3, "Country short name must have 3 characters");
        this.shortName = shortName;
    }

    @Override
    public String toString() {
        return "CountryTO{" +
                        "name='" + this.name + '\'' +
                        ", shortName='" + this.shortName + '\'' +
                        '}';
    }
}
