package org.example.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;
import org.example.entity.Game;
import org.example.entity.Game_;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class GameRepository extends AbstractRepository<Game, Long> {

    public GameRepository(EntityManager entityManager, CriteriaBuilder criteriaBuilder) {
        super(entityManager, Game.class, criteriaBuilder);
    }

    @Override
    public Optional<Game> findById(Long id) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Game> criteriaQuery = criteriaBuilder.createQuery(entityClass);
        Root<Game> root = criteriaQuery.from(entityClass);
        root.fetch(Game_.CATEGORY, JoinType.LEFT);
        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.equal(root.get(Game_.id), id));
        return Optional.ofNullable(entityManager.createQuery(criteriaQuery).getSingleResult());
    }
}
