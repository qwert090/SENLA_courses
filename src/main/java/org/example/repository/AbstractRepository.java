package org.example.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.example.entity.AbstractEntity;
import org.example.repository.interfaces.RepositoryApi;

import java.io.Serializable;
import java.util.List;

@RequiredArgsConstructor
public class AbstractRepository<T extends AbstractEntity, PK extends Serializable> implements RepositoryApi<T, Long> {

    @PersistenceContext
    protected final EntityManager entityManager;
    protected final Class<T> entityClass;
    protected final CriteriaBuilder criteriaBuilder;

    @Override
    public T findById(Long id) {
        return entityManager.find(entityClass, id);
    }

    @Override
    public List<T> findAll() {
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(entityClass);
        Root<T> root = criteriaQuery.from(entityClass);
        criteriaQuery.select(root);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public Long save(T entity) {
        entityManager.persist(entity);
        entityManager.flush();
        return entity.getId();
    }

    @Override
    public void update(T entity) {
        entityManager.merge(entity);
        entityManager.flush();
    }

    @Override
    public void deleteById(Long id) {
        CriteriaDelete<T> deleteQuery = criteriaBuilder.createCriteriaDelete(entityClass);
        Root<T> root = deleteQuery.from(entityClass);
        deleteQuery.where(criteriaBuilder.equal(root.get("id"), id));
        entityManager.createQuery(deleteQuery).executeUpdate();
        entityManager.flush();
    }
}
