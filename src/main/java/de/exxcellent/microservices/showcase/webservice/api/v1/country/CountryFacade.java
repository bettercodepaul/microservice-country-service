package de.exxcellent.microservices.showcase.webservice.api.v1.country;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
}
