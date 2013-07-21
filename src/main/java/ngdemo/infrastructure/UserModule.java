package ngdemo.infrastructure;

import com.google.inject.AbstractModule;
import ngdemo.repositories.contract.DummyRepository;
import ngdemo.repositories.contract.UserRepository;
import ngdemo.repositories.impl.mock.DummyMockRepositoryImpl;
import ngdemo.repositories.impl.mock.UserMockRepositoryImpl;
import ngdemo.service.contract.DummyService;
import ngdemo.service.contract.UserService;
import ngdemo.service.impl.DummyServiceImpl;
import ngdemo.service.impl.UserServiceImpl;

public class UserModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(DummyRepository.class).to(DummyMockRepositoryImpl.class);
        bind(DummyService.class).to(DummyServiceImpl.class);

        bind(UserRepository.class).to(UserMockRepositoryImpl.class);
        bind(UserService.class).to(UserServiceImpl.class);
    }
}
