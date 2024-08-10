package tpo.uade.api.mapper;

import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;

public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {
    @Override
    public Response toResponse(ConstraintViolationException e) { //TODO -> add logger
        return Response.status(Response.Status.BAD_REQUEST)
                .entity("Validation failed: " + e.getMessage())
                .build();
    }
}
