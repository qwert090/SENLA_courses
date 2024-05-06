package org.example.repository.impl;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.example.entity.AchievementRequest;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class AchievementRequestRepository extends AbstractRepository<AchievementRequest, Long> {

    public AchievementRequestRepository(EntityManager entityManager, CriteriaBuilder criteriaBuilder) {
        super(entityManager, AchievementRequest.class, criteriaBuilder);
    }

    @Override
    public Optional<AchievementRequest> findById(Long id) {
        EntityGraph<AchievementRequest> entityGraph = entityManager.createEntityGraph(AchievementRequest.class);
        TypedQuery<AchievementRequest> typedQuery = entityManager.createQuery(
                "SELECT a FROM AchievementRequest a WHERE a.id = :id", AchievementRequest.class
        );
        typedQuery.setParameter("id", id);
        entityGraph.addAttributeNodes("users");
        typedQuery.setHint("jakarta.persistence.loadgraph", entityGraph);
        return Optional.ofNullable(typedQuery.getSingleResult());
    }
}
