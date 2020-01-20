package de.exxcellent.microservices.showcase.webservice.api.v1.country;

import de.exxcellent.microservices.showcase.core.country.api.CountryBCI;
import de.exxcellent.microservices.showcase.core.country.api.types.CountryTO;

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

    @Inject
    private CountryBCI countryService;

    @GET
    public Set<CountryTO> getCountries() {
        return this.countryService.getCountries();
    }

    @GET
    @Path("{shortName}")
    public CountryTO getCountry(@PathParam("shortName") final String shortName) {
        return this.countryService.getCountry(shortName);
    }

    @POST
    public Set<CountryTO> addCountry(final CountryTO country) {
        return this.countryService.addCountry(country);
    }
}
