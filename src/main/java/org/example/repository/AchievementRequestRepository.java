package org.example.repository;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Getter;
import org.example.entity.AchievementRequest;
import org.springframework.stereotype.Repository;

@Repository
@Getter
public class AchievementRequestRepository extends AbstractRepository<AchievementRequest, Long> {

    public AchievementRequestRepository(EntityManager entityManager, CriteriaBuilder criteriaBuilder) {
        super(entityManager, AchievementRequest.class, criteriaBuilder);
    }

    @Override
    public AchievementRequest findById(Long id) {
        EntityGraph<AchievementRequest> entityGraph = entityManager.createEntityGraph(AchievementRequest.class);
        TypedQuery<AchievementRequest> typedQuery = entityManager.createQuery(
                "SELECT a FROM AchievementRequest a", AchievementRequest.class
        );
        entityGraph.addAttributeNodes("users");
        typedQuery.setHint("jakarta.persistence.loadgraph", entityGraph);
        return typedQuery.getSingleResult();
    }
}
