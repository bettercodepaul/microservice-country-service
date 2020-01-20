package de.exxcellent.microservices.showcase.core.country.impl.business;

import de.exxcellent.microservices.showcase.core.country.impl.persistence.model.CountryET;

import java.util.Set;

/**
 * Manages countries. Implementation of {@link CountryICI}.
 *
 * @author Felix Riess
 * @since 20.01.20
 */
public class CountryManager implements CountryICI {

    @Override
    public Set<CountryET> getCountries() {
        return null;
    }

    @Override
    public CountryET getCountry(final String shortName) {
        return null;
    }

    @Override
    public Set<CountryET> addCountry(final CountryET country) {
        return null;
    }
}
