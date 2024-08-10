package tpo.uade.api.resource;

import io.swagger.annotations.Api;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;
import tpo.uade.api.model.frontend.User;
import tpo.uade.api.service.CreateUserService;
import tpo.uade.api.service.GetUserDataService;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.util.NoSuchElementException;

@Api(value = "User Operations")
@Path("/user")
@Component
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    private final CreateUserService createUserService;
    private final GetUserDataService getUserDataService;

    public UserResource (CreateUserService createUserService, GetUserDataService getUserDataService) {
        this.createUserService = createUserService;
        this.getUserDataService = getUserDataService;
    }

    @POST
    public Response createUser (@NotNull User user) {
        try {
            //TODO -> add validator
            createUserService.createUser(user);
            return Response.ok().build();
        } catch (ConstraintViolationException e) { //TODO -> add logger for the catches
            return Response.status(Status.BAD_REQUEST).build();
        } catch (Exception e) { //TODO -> add logger for the catches
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    public Response getUserData (String username) {
        try {
            return Response.ok(getUserDataService.getUserData(username)).build();
        } catch (NoSuchElementException e) { //TODO -> add logger for the catches
            return Response.status(Status.NOT_FOUND).build();
        } catch (ConstraintViolationException e) { //TODO -> add logger for the catches
            return Response.status(Status.BAD_REQUEST).build();
        } catch (Exception e) { //TODO -> add logger for the catches
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
