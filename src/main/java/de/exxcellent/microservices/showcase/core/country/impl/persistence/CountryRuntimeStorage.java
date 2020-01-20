package de.exxcellent.microservices.showcase.core.country.impl.persistence;

import de.exxcellent.microservices.showcase.core.country.impl.persistence.model.CountryET;

import java.util.HashSet;
import java.util.Set;

/**
 * The implementation for {@link CountryRepository} of a runtime storage for countries.
 *
 * @author Felix Riess
 * @since 20.01.20
 */
public class CountryRuntimeStorage implements CountryRepository {
    /**
     * a {@link Set} containing all known countries as {@link CountryET} during runtime.
     */
    private Set<CountryET> countries;

    /**
     * Constructor.
     */
    public CountryRuntimeStorage() {
        this.countries = new HashSet<>();
        initData();
    }

    @Override
    public Set<CountryET> getCountries() {
        return this.countries;
    }

    @Override
    public CountryET getCountry(final String shortName) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<CountryET> addCountry(final CountryET country) {
        this.countries.add(country);
        return this.countries;
    }

    private void initData() {
        // TODO [FR] (20.01.2020): create some test data.
    }
}
