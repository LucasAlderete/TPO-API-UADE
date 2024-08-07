package tpo.uade.api.resource.implementation;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Component;
import tpo.uade.api.service.CreateUserService;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api(value = "User Operations")
@Path("/user")
@Component
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    private final CreateUserService createUserService;

    public UserResource (CreateUserService createUserService) {
        this.createUserService = createUserService;
    }

    @POST
    public Response createUser () { //TODO -> define user data to receive
        createUserService.createUser();
        return Response.ok().build();
    }
}
