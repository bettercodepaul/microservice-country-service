package de.exxcellent.microservices.showcase.core.language.api.types;

import de.exxcellent.microservices.showcase.common.validation.Preconditions;

import java.io.Serializable;

/**
 * Transport object (TO) holding the information about a language.
 *
 * @author Felix Riess, eXXcellent solutions consulting & software gmbh
 * @since 22.01.2020
 */
public class LanguageTO implements Serializable {
    /**
     * generated serialVersionUID
     */
    private static final long serialVersionUID = -741113964815463571L;
    /**
     * the short name (ID) of this {@link LanguageTO}.
     */
    private String shortName;
    /**
     * the name of this {@link LanguageTO}.
     */
    private String name;

    /**
     * empty constructor for JSON mapping.
     */
    public LanguageTO() {

    }

    /**
     * Constructor.
     *
     * @param shortName the short name (ID) of the language (3 characters, not {@code null}).
     * @param name the name of the language (not {@code null}).
     */
    public LanguageTO(final String shortName, final String name) {
        Preconditions.checkNotNull(shortName, "Language short name must not be null");
        Preconditions.checkNotNull(name, "Language name must not be null");
        Preconditions.checkStringLength(shortName, 3, "Language short name must have 3 characters");
        this.shortName = shortName;
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
     * @param shortName the shortName to set
     */
    public void setShortName(final String shortName) {
        Preconditions.checkNotNull(shortName, "Language short name must not be null");
        Preconditions.checkStringLength(shortName, 3, "Language short name must have 3 characters");
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
     * @param name the name to set
     */
    public void setName(final String name) {
        Preconditions.checkNotNull(name, "Language name must not be null");
        this.name = name;
    }

    @Override
    public String toString() {
        return "LanguageTO{" +
                        "shortName='" + this.shortName + '\'' +
                        ", name='" + this.name + '\'' +
                        '}';
    }
}
