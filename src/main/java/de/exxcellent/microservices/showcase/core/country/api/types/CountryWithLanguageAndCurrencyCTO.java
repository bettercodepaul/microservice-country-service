package de.exxcellent.microservices.showcase.core.country.api.types;

import de.exxcellent.microservices.showcase.common.validation.Preconditions;
import de.exxcellent.microservices.showcase.core.country.impl.access.CountryValidation;
import de.exxcellent.microservices.showcase.core.currency.api.types.CurrencyTO;
import de.exxcellent.microservices.showcase.core.language.api.types.LanguageTO;

import java.io.Serializable;

/**
 * A combined transport object holding the information about a country, its language and its currency.
 *
 * @author Felix Riess, eXXcellent solutions consulting & software gmbh
 * @since 23.01.2020
 */
public class CountryWithLanguageAndCurrencyCTO implements Serializable {
    /**
     * generated serialVersionUID
     */
    private static final long serialVersionUID = -1602188162099478824L;
    /**
     * the country of this {@link CountryWithLanguageAndCurrencyCTO}
     */
    private CountryTO country;
    /**
     * the language of this {@link CountryWithLanguageAndCurrencyCTO}
     */
    private LanguageTO language;
    /**
     * the currency of this {@link CountryWithLanguageAndCurrencyCTO}.
     */
    private CurrencyTO currency;

    /**
     * empty constructor for JSON mapping.
     */
    public CountryWithLanguageAndCurrencyCTO() {

    }

    /**
     * Constructor.
     *
     * @param country the information about the country as {@link CountryTO}.
     * @param language the information about the language as {@link LanguageTO}.
     * @param currency the information about the currency as {@link CurrencyTO}.
     */
    public CountryWithLanguageAndCurrencyCTO(final CountryTO country, final LanguageTO language, final CurrencyTO currency) {
        CountryValidation.validateCountryTO(country);
        Preconditions.checkNotNull(language, "Language must not be null");
        Preconditions.checkNotNull(language.getShortName(), "Language short name must not be null");
        Preconditions.checkNotNull(language.getName(), "Language name must not be null");
        Preconditions.checkStringLength(language.getShortName(), 3, "Language short name must have 3 characters");
        Preconditions.checkNotNull(currency, "Currency must not be null");
        Preconditions.checkNotNull(currency.getShortName(), "Currency short name must not be null");
        Preconditions.checkStringLength(currency.getShortName(), 3, "Currency short name must have 3 characters");
        Preconditions.checkNotNull(currency.getName(), "Currency name must not be null");
        this.country = country;
        this.language = language;
        this.currency = currency;
    }

    public CountryTO getCountry() {
        return this.country;
    }

    public void setCountry(final CountryTO country) {
        CountryValidation.validateCountryTO(country);
        this.country = country;
    }

    public LanguageTO getLanguage() {
        return this.language;
    }

    public void setLanguage(final LanguageTO language) {
        Preconditions.checkNotNull(language, "Language must not be null");
        Preconditions.checkNotNull(language.getShortName(), "Language short name must not be null");
        Preconditions.checkNotNull(language.getName(), "Language name must not be null");
        Preconditions.checkStringLength(language.getShortName(), 3, "Language short name must have 3 characters");
        this.language = language;
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
        return "CountryWithLanguageAndCurrencyCTO{" +
                        "country=" + this.country +
                        ", language=" + this.language +
                        ", currency=" + this.currency +
                        '}';
    }
}
