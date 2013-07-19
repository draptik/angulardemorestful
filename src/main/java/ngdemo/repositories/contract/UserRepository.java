package ngdemo.repositories.contract;

import ngdemo.domain.User;

import java.util.List;

public interface UserRepository {
    User getDefaultUser();

    List<User> getAllUsers();
}
