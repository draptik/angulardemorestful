package ngdemo.repositories.impl.mock;

import com.google.inject.Singleton;
import ngdemo.domain.User;
import ngdemo.repositories.contract.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Singleton
public class UserMockRepositoryImpl extends GenericMockRepository<User> implements UserRepository {

    private List<User> users = new ArrayList<>();

    public UserMockRepositoryImpl() {
        this.users = this.createUsers();
    }

    public User getById(String id) {
        return new User();
    }

    public List<User> getAll() {
        return this.users;
    }

    private List<User> createUsers() {
        int numberOfUsers = 10;
        for (int i = 0; i < numberOfUsers; i++) {
            User user = new User();
            user.setFirstName("Foo" + (i + 1));
            user.setLastName("Bar" + (i + 1));
            this.users.add(user);
        }
        return this.users;
    }
}
