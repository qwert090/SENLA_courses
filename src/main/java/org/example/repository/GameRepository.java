package org.example.repository;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Getter;
import org.example.entity.Game;
import org.springframework.stereotype.Repository;

@Repository
@Getter
public class GameRepository extends AbstractRepository<Game, Long> {


    public GameRepository(EntityManager entityManager, CriteriaBuilder criteriaBuilder) {
        super(entityManager, Game.class, criteriaBuilder);
    }

    @Override
    public Game findById(Long id){
        EntityGraph<Game> entityGraph = entityManager.createEntityGraph(Game.class);
        TypedQuery<Game> typedQuery = entityManager.createQuery("SELECT g FROM Game g", Game.class);
        entityGraph.addAttributeNodes("category");
        typedQuery.setHint("jakarta.persistence.loadgraph", entityGraph);
        return typedQuery.getSingleResult();
    }
}
