package org.example.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Getter;
import org.example.entity.Category;
import org.example.repository.impl.AbstractRepository;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryRepository extends AbstractRepository<Category, Long> {


    public CategoryRepository(EntityManager entityManager, CriteriaBuilder criteriaBuilder) {
        super(entityManager, Category.class, criteriaBuilder);
    }

    @Override
    public Category findById(Long id){
        TypedQuery<Category> userTypedQuery = entityManager.createQuery("SELECT c  FROM Category c WHERE c.id = :id", Category.class)
                .setParameter("id", id);
        return userTypedQuery.getSingleResult();
    }
}
