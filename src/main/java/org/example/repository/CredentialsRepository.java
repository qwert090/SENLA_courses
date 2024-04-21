package org.example.repository;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.example.entity.Credentials;

public class CredentialsRepository extends AbstractRepository<Credentials, Long> {

    public CredentialsRepository(EntityManager entityManager, CriteriaBuilder criteriaBuilder) {
        super(entityManager, Credentials.class, criteriaBuilder);
    }

    @Override
    public Credentials findById(Long id) {
        EntityGraph<Credentials> entityGraph = entityManager.createEntityGraph(Credentials.class);
        TypedQuery<Credentials> typedQuery = entityManager.createQuery("SELECT c FROM Credentials c", Credentials.class);
        entityGraph.addAttributeNodes("roles");
        typedQuery.setHint("jakarta.persistence.loadgraph", entityGraph);
        return typedQuery.getSingleResult();
    }
}
