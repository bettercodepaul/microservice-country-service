package de.exxcellent.microservices.showcase.core.country.impl.business;

import de.exxcellent.microservices.showcase.common.errorhandling.ErrorCode;
import de.exxcellent.microservices.showcase.common.errorhandling.exception.BusinessException;
import de.exxcellent.microservices.showcase.common.validation.Preconditions;
import de.exxcellent.microservices.showcase.core.country.impl.access.CountryValidation;
import de.exxcellent.microservices.showcase.core.country.impl.persistence.CountryRepository;
import de.exxcellent.microservices.showcase.core.country.impl.persistence.model.CountryET;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;

/**
 * Manages countries. Implementation of {@link CountryICI}.
 *
 * @author Felix Riess
 * @since 20.01.20
 */
@ApplicationScoped
@Transactional
public class CountryManager implements CountryICI {

    @Inject
    private CountryRepository countryRepository;

    @Override
    public Set<CountryET> getCountries() {
        return this.countryRepository.findAll();
    }

    @Override
    public CountryET getCountry(final String shortName) {
        Preconditions.checkNotNull(shortName, "Country short name must not be null");
        final Optional<CountryET> optionalCountry = this.countryRepository.findByShortName(shortName);
        if(optionalCountry.isPresent()) {
            return optionalCountry.get();
        } else {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "Country with short name " + shortName + " is not existing");
        }
    }

    @Override
    public Set<CountryET> addCountry(final CountryET country) {
        CountryValidation.validateCountryET(country);
        final Optional<CountryET> optionalCountryET = this.countryRepository.findByShortName(country.getShortName());
        if(optionalCountryET.isPresent()) {
            final CountryET existingCountry = optionalCountryET.get();
            if(!existingCountry.getName().equalsIgnoreCase(country.getName())) {
                // another country with this short name is already existing. No more country with this short name can be created.
                throw new BusinessException(ErrorCode.INVALID_ARGUMENT_ERROR, "A country with the short name "
                        + country.getShortName() + " is already existing: " + existingCountry.getName()
                        + ". Cannot create two countries with the same short name");
            } // else: country is already existing and must not be added again.
        } else {
            // no country with the provided short name is present. Create a new one.
            this.countryRepository.addCountry(country);
        }
        return this.countryRepository.findAll();
    }
}
