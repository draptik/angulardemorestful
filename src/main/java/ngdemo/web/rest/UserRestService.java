package ngdemo.web.rest;

import com.google.inject.Inject;
import ngdemo.domain.User;
import ngdemo.service.contract.UserService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;
import java.util.List;

@Provider
@Path("/users")
public class UserRestService {

    private final UserService userService;

    @Inject
    public UserRestService(UserService userService) {
        this.userService = userService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getAllUsersInJSON() {
        return userService.getAllUsers();
    }
//
//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public User getUserById(@PathParam("id") String id) {
//        return userService.getById(id);
//    }
}
