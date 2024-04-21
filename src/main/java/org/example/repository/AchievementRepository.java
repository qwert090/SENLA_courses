package org.example.repository;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Getter;
import org.example.entity.Achievement;
import org.springframework.stereotype.Repository;

@Repository
@Getter
public class AchievementRepository extends AbstractRepository<Achievement, Long> {

    public AchievementRepository(EntityManager entityManager, CriteriaBuilder criteriaBuilder) {
        super(entityManager, Achievement.class, criteriaBuilder);
    }

    @Override
    public Achievement findById(Long id) {
        EntityGraph<Achievement> entityGraph = entityManager.createEntityGraph(Achievement.class);
        TypedQuery<Achievement> typedQuery = entityManager.createQuery("SELECT a FROM Achievement a", Achievement.class);
        entityGraph.addAttributeNodes("game");
        typedQuery.setHint("jakarta.persistence.loadgraph", entityGraph);
        return typedQuery.getSingleResult();
    }
}
