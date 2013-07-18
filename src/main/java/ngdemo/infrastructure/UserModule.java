package ngdemo.infrastructure;

import com.google.inject.AbstractModule;
import ngdemo.service.contract.UserFactory;
import ngdemo.service.contract.UserService;
import ngdemo.service.impl.UserFactoryImpl;
import ngdemo.service.impl.UserServiceImpl;

public class UserModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(UserFactory.class).to(UserFactoryImpl.class);
        bind(UserService.class).to(UserServiceImpl.class);
    }
}
