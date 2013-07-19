package ngdemo.repositories.contract;

import ngdemo.domain.User;

public interface UserRepository<T extends User> extends Repository<T, String> {
}
