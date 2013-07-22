package ngdemo.service.contract;

import ngdemo.domain.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getById(int id);

    User createNewUser(User user);
}
