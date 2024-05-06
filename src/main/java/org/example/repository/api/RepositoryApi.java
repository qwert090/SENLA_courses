package org.example.repository.api;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface RepositoryApi<T, PK extends Serializable> {
    List<T> findAll();

    Optional<T> findById(PK id);

    void save(T entity);

    void update(T entity);

    void deleteById(PK id);
}
