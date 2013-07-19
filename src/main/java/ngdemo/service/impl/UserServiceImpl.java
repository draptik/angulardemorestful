package ngdemo.service.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import ngdemo.repositories.contract.UserRepository;
import ngdemo.service.contract.UserService;

import java.util.List;

@Singleton
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Inject
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List getAllUsers() {
        return this.userRepository.getAll();
    }

}
