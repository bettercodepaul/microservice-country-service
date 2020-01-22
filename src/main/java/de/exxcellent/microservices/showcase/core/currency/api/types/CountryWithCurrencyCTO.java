package de.exxcellent.microservices.showcase.core.currency.api.types;

import de.exxcellent.microservices.showcase.common.validation.Preconditions;

import java.io.Serializable;

/**
 * A combined transport object (CTO) holding the information about a country and the currency.
 * Used for calls to currency service application.
 *
 * @author Felix Riess, eXXcellent solutions consulting & software gmbh
 * @since 21.01.2020
 */
public class CountryWithCurrencyCTO implements Serializable {
    /**
     * generated serialVersionUID
     */
    private static final long serialVersionUID = 2307411168986583466L;
    /**
     * The country short name (3 characters)
     */
    private String countryShortName;
    /**
     * the currency for the country as {@link CurrencyTO}.
     */
    private CurrencyTO currency;

    /**
     * empty constructor for JSON mapping.
     */
    public CountryWithCurrencyCTO() {

    }

    /**
     * Constructor.
     *
     * @param countryShortName the short name of the country (3 characters, not {@code null}).
     * @param currency the currency of the country as {@link CurrencyTO}.
     */
    public CountryWithCurrencyCTO(final String countryShortName, final CurrencyTO currency) {
        Preconditions.checkNotNull(countryShortName, "Country short name must not be null");
        Preconditions.checkStringLength(countryShortName, 3, "Country short name must have 3 characters");
        Preconditions.checkNotNull(currency, "Currency must not be null");
        Preconditions.checkNotNull(currency.getShortName(), "Currency short name must not be null");
        Preconditions.checkStringLength(currency.getShortName(), 3, "Currency short name must have 3 characters");
        Preconditions.checkNotNull(currency.getName(), "Currency name must not be null");
        this.countryShortName = countryShortName;
        this.currency = currency;
    }

    public String getCountryShortName() {
        return this.countryShortName;
    }

    public void setCountryShortName(final String countryShortName) {
        Preconditions.checkNotNull(countryShortName, "Country short name must not be null");
        Preconditions.checkStringLength(countryShortName, 3, "Country short name must have 3 characters");
        this.countryShortName = countryShortName;
    }

    public CurrencyTO getCurrency() {
        return this.currency;
    }

    public void setCurrency(final CurrencyTO currency) {
        Preconditions.checkNotNull(currency, "Currency must not be null");
        Preconditions.checkNotNull(currency.getShortName(), "Currency short name must not be null");
        Preconditions.checkStringLength(currency.getShortName(), 3, "Currency short name must have 3 characters");
        Preconditions.checkNotNull(currency.getName(), "Currency name must not be null");
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "CountryWithCurrencyCTO{" +
                        "countryShortName='" + this.countryShortName + '\'' +
                        ", currency=" + this.currency +
                        '}';
    }
}
