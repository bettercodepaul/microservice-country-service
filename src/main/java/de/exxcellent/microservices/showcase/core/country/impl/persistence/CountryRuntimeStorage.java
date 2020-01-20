package de.exxcellent.microservices.showcase.core.country.impl.persistence;

import de.exxcellent.microservices.showcase.core.country.impl.persistence.model.CountryET;

import javax.enterprise.context.ApplicationScoped;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * The implementation for {@link CountryRepository} of a runtime storage for countries.
 *
 * @author Felix Riess
 * @since 20.01.20
 */
@ApplicationScoped
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
    public Set<CountryET> findAll() {
        return this.countries;
    }

    @Override
    public Optional<CountryET> findByShortName(final String shortName) {
        return this.countries.stream().filter(c -> shortName.equalsIgnoreCase(c.getShortName())).findFirst();
    }

    @Override
    public Set<CountryET> addCountry(final CountryET country) {
        this.countries.add(country);
        return this.countries;
    }

    private void initData() {
        final CountryET germany = new CountryET("Germany", "GER");
        final CountryET france = new CountryET("France", "FRA");
        final CountryET scotland = new CountryET("Scotland", "SCO");
        this.countries.add(germany);
        this.countries.add(france);
        this.countries.add(scotland);
    }
}
