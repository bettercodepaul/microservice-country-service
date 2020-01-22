package de.exxcellent.microservices.showcase.core.currency.api.types;

import de.exxcellent.microservices.showcase.common.validation.Preconditions;

import java.io.Serializable;

/**
 * Transport object (TO) holding the information about a currency.
 *
 * @author Felix Riess, eXXcellent solutions consulting & software gmbh
 * @since 21.01.2020
 */
public class CurrencyTO implements Serializable {
    /**
     * generated serialVersionUID
     */
    private static final long serialVersionUID = 432841563642673310L;
    /**
     * the short name (ISO Code) of the currency.
     */
    private String shortName;
    /**
     * the name of the currency
     */
    private String name;

    /**
     * empty constructor for JSON mapping.
     */
    public CurrencyTO() {

    }

    /**
     * Constructor.
     *
     * @param shortName the short name (ISO Code) of the currency (3 characters, not {@code null}).
     * @param name the name of the currency (not {@code null}).
     */
    public CurrencyTO(final String shortName, final String name) {
        Preconditions.checkNotNull(shortName, "Currency short name must not be null");
        Preconditions.checkStringLength(shortName, 3, "Currency short name must have 3 characters");
        Preconditions.checkNotNull(name, "Currency name must not be null");
        this.shortName = shortName;
        this.name = name;
    }

    public String getShortName() {
        return this.shortName;
    }

    public void setShortName(final String shortName) {
        Preconditions.checkNotNull(shortName, "Currency short name must not be null");
        Preconditions.checkStringLength(shortName, 3, "Currency short name must have 3 characters");
        this.shortName = shortName;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        Preconditions.checkNotNull(name, "Currency name must not be null");
        this.name = name;
    }

    @Override
    public String toString() {
        return "CurrencyTO{" +
                        "shortName='" + this.shortName + '\'' +
                        ", name='" + this.name + '\'' +
                        '}';
    }
}
