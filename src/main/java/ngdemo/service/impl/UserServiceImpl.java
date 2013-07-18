package ngdemo.service.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import ngdemo.domain.User;
import ngdemo.service.contract.UserFactory;
import ngdemo.service.contract.UserService;

import java.util.List;

@Singleton
public class UserServiceImpl implements UserService {

    private final UserFactory userFactory;

    @Inject
    public UserServiceImpl(UserFactory userFactory) {
        this.userFactory = userFactory;
    }

    @Override
    public List<User> getDefaultUsers() {
        return this.userFactory.createUsers();
    }

    @Override
    public User getDefaultUser() {
        return this.userFactory.createUser();
    }

}
