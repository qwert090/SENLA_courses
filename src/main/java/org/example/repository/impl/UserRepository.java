package org.example.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.example.entity.User;
import org.example.entity.User_;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepository extends AbstractRepository<User, Long> {

    public UserRepository(EntityManager entityManager, CriteriaBuilder criteriaBuilder) {
        super(entityManager, User.class, criteriaBuilder);
    }

    @Override
    public Optional<User> findById(Long id) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(entityClass);
        Root<User> root = criteriaQuery.from(entityClass);
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get(User_.ID), id));
        User user = entityManager.createQuery(criteriaQuery).getSingleResult();
        return Optional.ofNullable(user);
    }

    public Optional<User> findByEmail(String email) {
        TypedQuery<User> userTypedQuery = entityManager.createQuery(
                "SELECT u FROM User u JOIN FETCH u.credentials c JOIN FETCH u.roles WHERE c.email = :email", User.class
        ).setParameter("email", email);

        return Optional.ofNullable(userTypedQuery.getSingleResult());
    }
}
