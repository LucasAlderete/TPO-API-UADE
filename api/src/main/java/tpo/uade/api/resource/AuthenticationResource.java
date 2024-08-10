package tpo.uade.api.resource;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Component;
import tpo.uade.api.service.AuthenticationService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api(value = "Authentication Operations")
@Path("/auth")
@Component
@Produces(MediaType.APPLICATION_JSON)
public class AuthenticationResource {

    private final AuthenticationService authenticationService;

    public AuthenticationResource(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @GET
    public Response authenticate () { //TODO -> dev it
        return Response.ok().build();
    }
}
