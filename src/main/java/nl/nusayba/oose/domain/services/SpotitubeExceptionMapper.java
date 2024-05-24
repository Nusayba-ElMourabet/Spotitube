package nl.nusayba.oose.domain.services;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import nl.nusayba.oose.domain.exceptions.AuthenticationException;
import nl.nusayba.oose.domain.exceptions.SpotitubeException;

@Provider
public class SpotitubeExceptionMapper implements ExceptionMapper<SpotitubeException> {

    @Override
    public Response toResponse(SpotitubeException e) {
        if (e instanceof AuthenticationException){
            return Response.status(401).build();
        }
        return Response.status(500).build();
    }
}
