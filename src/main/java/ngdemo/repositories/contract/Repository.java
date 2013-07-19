package ngdemo.repositories.contract;

import java.util.List;

public interface Repository<T, TId> {
    List<? extends T> getAll();

    T getById(TId id);
}
