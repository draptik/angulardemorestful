package ngdemo.repositories.impl;

import ngdemo.domain.User;
import ngdemo.repositories.contract.DummyRepository;

public class DummyRepositoryImpl<T> extends AbstractRepository<User, String> implements DummyRepository<User> {

    @Override
    public User getDefaultUser() {
        User user = new User();
        user.setFirstName("JonFromREST");
        user.setLastName("DoeFromREST");
        return user;
    }
}
