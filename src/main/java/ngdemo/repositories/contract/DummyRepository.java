package ngdemo.repositories.contract;

import ngdemo.domain.User;

public interface DummyRepository<T extends User> extends Repository<T, String> {
    T getDefaultUser();
}
