package de.exxcellent.microservices.showcase.webservice.api.v1.country;

import de.exxcellent.microservices.showcase.common.errorhandling.ErrorCode;
import de.exxcellent.microservices.showcase.common.errorhandling.exception.BusinessException;
import de.exxcellent.microservices.showcase.common.validation.Preconditions;
import de.exxcellent.microservices.showcase.core.country.api.CountryBCI;
import de.exxcellent.microservices.showcase.core.country.api.types.CountryTO;
import de.exxcellent.microservices.showcase.core.country.impl.access.CountryValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Set;

/**
 * Provides a REST API for the country service application.
 *
 * @author Felix Riess
 * @since 20.01.20
 */
@Path("/api/v1/countries")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CountryFacade {
    /**
     * The {@link Logger} for this {@link CountryFacade}.
     */
    private static final Logger LOG = LoggerFactory.getLogger(CountryFacade.class);

    @Inject
    private CountryBCI countryService;

    /**
     * Get all available countries.
     *
     * @return a {@link Set} containing all countries as {@link CountryTO}.
     * @exception BusinessException with {@link ErrorCode#EMPTY_LIST_ERROR} if no countries are available to produce HTTP 204.
     */
    @GET
    public Set<CountryTO> getCountries() {
        LOG.info("Resource to get all countries triggered");
        Set<CountryTO> countries = this.countryService.getCountries();
        if(countries.isEmpty()) {
            throw new BusinessException(ErrorCode.EMPTY_LIST_ERROR, "No Countries are available");
        } else {
            return countries;
        }
    }

    /**
     * Get the country with the provided short name (ID).
     *
     * @param shortName the short name (ID) of the country to be returned (3 characters long, not {@code null}).
     * @return the country with the given short name as {@link CountryTO}.
     */
    @GET
    @Path("{shortName}")
    public CountryTO getCountry(@PathParam("shortName") final String shortName) {
        Preconditions.checkNotNull(shortName, "Country short name must not be null");
        Preconditions.checkStringLength(shortName, 3, "Country short name must have 3 characters");
        LOG.info("Resource to get country with short name {} triggered", shortName);
        return this.countryService.getCountry(shortName);
    }

    /**
     * Add a new country to the existing ones.
     *
     * @param country the country to be added as {@link CountryTO}. (must be valid, see {@link CountryValidation#validateCountryTO(CountryTO)}).
     * @return all available countries including the new one as {@link Set} of {@link CountryTO}s.
     * @exception BusinessException with {@link ErrorCode#EMPTY_LIST_ERROR} if no countries are available to produce HTTP 204.
     */
    @POST
    public Set<CountryTO> addCountry(final CountryTO country) {
        CountryValidation.validateCountryTO(country);
        LOG.info("Resource to add country with name {} and short name {} triggered", country.getName(), country.getShortName());
        Set<CountryTO> countries = this.countryService.addCountry(country);
        if(countries.isEmpty()) {
            throw new BusinessException(ErrorCode.EMPTY_LIST_ERROR, "No Countries are available");
        } else {
            return countries;
        }
    }
}
