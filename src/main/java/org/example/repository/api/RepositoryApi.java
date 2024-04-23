package org.example.repository.api;

import java.io.Serializable;
import java.util.List;

public interface RepositoryApi<T, PK extends Serializable> {
    List<T> findAll();

    T findById(PK id);

    void save(T entity);

    void update(T entity);

    void deleteById(PK id);
}
