package org.example.repository.impl;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Getter;
import org.example.entity.Game;
import org.example.repository.impl.AbstractRepository;
import org.springframework.stereotype.Repository;

@Repository
public class GameRepository extends AbstractRepository<Game, Long> {


    public GameRepository(EntityManager entityManager, CriteriaBuilder criteriaBuilder) {
        super(entityManager, Game.class, criteriaBuilder);
    }

    @Override
    public Game findById(Long id){
        EntityGraph<Game> entityGraph = entityManager.createEntityGraph(Game.class);
        TypedQuery<Game> typedQuery = entityManager.createQuery("SELECT g FROM Game g WHERE g.id = :id", Game.class);
        typedQuery.setParameter("id", id);
        entityGraph.addAttributeNodes("category");
        typedQuery.setHint("jakarta.persistence.loadgraph", entityGraph);
        return typedQuery.getSingleResult();
    }
}
