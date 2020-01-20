package de.exxcellent.microservices.showcase.core.country.impl.access;

import de.exxcellent.microservices.showcase.core.country.api.CountryBCI;
import de.exxcellent.microservices.showcase.core.country.api.types.CountryTO;

import java.util.Set;

/**
 * The business facade (BF) of the country component. Implements the {@link CountryBCI}.
 *
 * @author Felix Riess
 * @since 20.01.20
 */
public class CountryBF implements CountryBCI {
    @Override
    public Set<CountryTO> getCountries() {
        return null;
    }

    @Override
    public CountryTO getCountry(final String shortName) {
        return null;
    }

    @Override
    public Set<CountryTO> addCountry(final CountryTO country) {
        return null;
    }
}
