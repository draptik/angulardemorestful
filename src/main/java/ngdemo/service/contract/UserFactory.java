package ngdemo.service.contract;

import ngdemo.domain.User;

import java.util.List;

public interface UserFactory {
    User createUser();

    List<User> createUsers();
}
