package ngdemo.repositories.impl;

import ngdemo.repositories.contract.Repository;

import java.util.List;

public class AbstractRepository<T, TId> implements Repository<T, TId> {

    @Override
    public List<? extends T> getAll() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public T getById(TId tId) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
