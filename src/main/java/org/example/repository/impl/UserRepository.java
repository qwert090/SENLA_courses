package org.example.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.example.entity.User;
import org.example.entity.User_;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository extends AbstractRepository<User, Long> {

    public UserRepository(EntityManager entityManager, CriteriaBuilder criteriaBuilder) {
        super(entityManager, User.class, criteriaBuilder);
    }

    @Override
    public User findById(Long id) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(entityClass);
        Root<User> root = criteriaQuery.from(entityClass);
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get(User_.ID), id));
        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }
}
