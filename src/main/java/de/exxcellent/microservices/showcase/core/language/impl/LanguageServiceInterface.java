package de.exxcellent.microservices.showcase.core.language.impl;

import de.exxcellent.microservices.showcase.core.language.api.types.CountryWithLanguageCTO;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * This interface defines the REST API of the language service and is used as a proxy.
 * @see "https://www.baeldung.com/resteasy-client-tutorial"
 *
 * @author Felix Riess, eXXcellent solutions consulting & software gmbh
 * @since 22.01.2020
 */
@Path("/api/v1/countries-with-language")
@Consumes(MediaType.APPLICATION_JSON)
@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
@RegisterRestClient
public interface LanguageServiceInterface {
    @GET
    Response getCountriesWithLanguage();

    @GET
    @Path("{countryShortName}")
    Response getCountryWithLanguage(@PathParam("countryShortName") final String countryShortName);

    @POST
    Response createCountryWithLanguage(final CountryWithLanguageCTO countryWithLanguage);
}
