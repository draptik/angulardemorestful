package ngdemo.infrastructure;

import com.google.inject.AbstractModule;
import ngdemo.repositories.contract.UserRepository;
import ngdemo.repositories.impl.UserRepositoryImpl;
import ngdemo.service.contract.UserService;
import ngdemo.service.impl.UserServiceImpl;

public class UserModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(UserRepository.class).to(UserRepositoryImpl.class);
        bind(UserService.class).to(UserServiceImpl.class);
    }
}
