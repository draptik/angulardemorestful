package ngdemo.service.contract;

import ngdemo.domain.User;

import java.util.List;

public interface UserService {
    List<User> getDefaultUsers();

    User getDefaultUser();
}
