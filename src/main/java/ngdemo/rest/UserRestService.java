package ngdemo.rest;

import com.google.inject.Inject;
import ngdemo.domain.User;
import ngdemo.service.contract.UserService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/users")
public class UserRestService {

    private final UserService userService;

    @Inject
    public UserRestService(UserService userService) {
        this.userService = userService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public User getDefaultUserInJSON() {
        return userService.getDefaultUser();
    }
}
