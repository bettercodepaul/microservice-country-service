package de.exxcellent.microservices.showcase.webservice.exceptionmapper;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 * Maps {@link RuntimeException}s to HTTP responses.
 *
 * @author Felix Riess, eXXcellent solutions consulting & software gmbh
 * @since 21.01.2020
 */
public class RuntimeExceptionMapper implements ExceptionMapper<RuntimeException> {

    @Override
    public Response toResponse(final RuntimeException e) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                       .entity(e.getMessage())
                       .type(MediaType.TEXT_PLAIN_TYPE)
                       .build();
    }
}
