package org.example.repository.impl;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.example.entity.Achievement;
import org.springframework.stereotype.Repository;

@Repository
public class AchievementRepository extends AbstractRepository<Achievement, Long> {

    public AchievementRepository(EntityManager entityManager, CriteriaBuilder criteriaBuilder) {
        super(entityManager, Achievement.class, criteriaBuilder);
    }

    @Override
    public Achievement findById(Long id) {
        EntityGraph<Achievement> entityGraph = entityManager.createEntityGraph(Achievement.class);
        TypedQuery<Achievement> typedQuery = entityManager.createQuery("SELECT a FROM Achievement a WHERE a.id = :id", Achievement.class);
        typedQuery.setParameter("id", id);
        entityGraph.addAttributeNodes("game");
        typedQuery.setHint("jakarta.persistence.loadgraph", entityGraph);
        return typedQuery.getSingleResult();
    }

}
