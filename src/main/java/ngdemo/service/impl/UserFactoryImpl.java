package ngdemo.service.impl;

import ngdemo.domain.User;
import ngdemo.service.contract.UserFactory;

import java.util.ArrayList;
import java.util.List;

public class UserFactoryImpl implements UserFactory {

    @Override
    public User createUser() {
        User user = new User();
        user.setFirstName("JonFromREST");
        user.setLastName("DoeFromREST");
        return user;
    }

    @Override
    public List<User> createUsers() {
        ArrayList<User> users = new ArrayList<User>();
        users.add(createUser());
        return users;
    }

}
