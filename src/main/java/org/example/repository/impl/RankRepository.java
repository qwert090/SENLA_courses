package org.example.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.example.entity.Rank;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class RankRepository extends AbstractRepository<Rank, Long> {


    public RankRepository(EntityManager entityManager, CriteriaBuilder criteriaBuilder) {
        super(entityManager, Rank.class, criteriaBuilder);
    }

    @Override
    public Optional<Rank> findById(Long id){
        TypedQuery<Rank> userTypedQuery = entityManager.createQuery("SELECT r  FROM Rank r WHERE r.id = :id", Rank.class)
                .setParameter("id", id);
        return Optional.ofNullable(userTypedQuery.getSingleResult());
    }
}
