package org.example.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.example.entity.Category;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class CategoryRepository extends AbstractRepository<Category, Long> {


    public CategoryRepository(EntityManager entityManager, CriteriaBuilder criteriaBuilder) {
        super(entityManager, Category.class, criteriaBuilder);
    }

    @Override
    public Optional<Category> findById(Long id){
        TypedQuery<Category> userTypedQuery = entityManager.createQuery("SELECT c  FROM Category c WHERE c.id = :id", Category.class)
                .setParameter("id", id);
        return Optional.ofNullable(userTypedQuery.getSingleResult());
    }
}
