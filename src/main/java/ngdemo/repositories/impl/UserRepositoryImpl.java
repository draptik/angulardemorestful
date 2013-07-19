package ngdemo.repositories.impl;

import ngdemo.domain.User;
import ngdemo.repositories.contract.UserRepository;

public class UserRepositoryImpl<T> extends AbstractRepository<User, String> implements UserRepository<User> {
}
