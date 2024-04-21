package org.example.repository.interfaces;

import java.io.Serializable;
import java.util.List;

public interface RepositoryApi<T, PK extends Serializable> {
    List<T> findAll();

    T findById(PK id);

    Long save(T entity);

    void update(T entity);

    void deleteById(PK id);
}
