package de.exxcellent.microservices.showcase.core.country.impl.access;

import de.exxcellent.microservices.showcase.common.validation.Preconditions;
import de.exxcellent.microservices.showcase.core.country.api.CountryBCI;
import de.exxcellent.microservices.showcase.core.country.api.types.CountryTO;
import de.exxcellent.microservices.showcase.core.country.impl.business.CountryICI;
import de.exxcellent.microservices.showcase.core.country.impl.persistence.model.CountryET;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * The business facade (BF) of the country component. Implements the {@link CountryBCI}.
 *
 * @author Felix Riess
 * @since 20.01.20
 */
@ApplicationScoped
public class CountryBF implements CountryBCI {

    @Inject
    private CountryICI countryManager;

    @Override
    public Set<CountryTO> getCountries() {
        return this.countryManager.getCountries().stream().map(CountryMapper::toTO).collect(Collectors.toSet());
    }

    @Override
    public CountryTO getCountry(final String shortName) {
        Preconditions.checkNotNull(shortName, "Country short name must not be null");
        return CountryMapper.toTO(this.countryManager.getCountry(shortName));
    }

    @Override
    public Set<CountryTO> addCountry(final CountryTO country) {
        CountryValidation.validateCountryTO(country);
        final CountryET mappedCountryET = CountryMapper.fromTO(country);
        return this.countryManager.addCountry(mappedCountryET).stream().map(CountryMapper::toTO).collect(Collectors.toSet());
    }
}
