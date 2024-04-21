package org.example.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Getter;
import org.example.entity.Rank;
import org.springframework.stereotype.Repository;

@Repository
@Getter
public class RankRepository extends AbstractRepository<Rank, Long> {


    public RankRepository(EntityManager entityManager, CriteriaBuilder criteriaBuilder) {
        super(entityManager, Rank.class, criteriaBuilder);
    }

    @Override
    public Rank findById(Long id){
        TypedQuery<Rank> userTypedQuery = entityManager.createQuery("SELECT r  FROM Rank r WHERE r.id = :id", Rank.class)
                .setParameter("id", id);
        return userTypedQuery.getSingleResult();
    }
}
