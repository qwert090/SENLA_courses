package org.example.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.example.entity.Credentials;
import org.springframework.stereotype.Repository;

@Repository
public class CredentialsRepository extends AbstractRepository<Credentials, Long> {

    public CredentialsRepository(EntityManager entityManager, CriteriaBuilder criteriaBuilder) {
        super(entityManager, Credentials.class, criteriaBuilder);
    }

    @Override
    public Credentials findById(Long id) {
        TypedQuery<Credentials> typedQuery = entityManager.createQuery(
                "SELECT c FROM Credentials c LEFT JOIN FETCH c.roles WHERE c.id = :id", Credentials.class
        );
        typedQuery.setParameter("id", id);
        return typedQuery.getSingleResult();
    }
}
