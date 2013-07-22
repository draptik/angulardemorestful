package ngdemo.repositories.contract;

import ngdemo.domain.User;

public interface UserRepository extends Repository<User> {
    User create(User user);
}
