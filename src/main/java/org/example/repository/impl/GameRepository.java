package org.example.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.example.entity.AbstractEntity_;
import org.example.entity.Category;
import org.example.entity.Game;
import org.springframework.stereotype.Repository;

@Repository
public class GameRepository extends AbstractRepository<Game, Long> {


    public GameRepository(EntityManager entityManager, CriteriaBuilder criteriaBuilder) {
        super(entityManager, Game.class, criteriaBuilder);
    }

    @Override
    public Game findById(Long id) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Game> criteriaQuery = criteriaBuilder.createQuery(Game.class);
        Root<Game> root = criteriaQuery.from(Game.class);
        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.equal(root.get(AbstractEntity_.id), id));
        Join<Game, Category> categoryJoin = root.join("category", JoinType.LEFT);
        Fetch<Game, Category> categoryFetch = root.fetch("category", JoinType.LEFT);
        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }
}
