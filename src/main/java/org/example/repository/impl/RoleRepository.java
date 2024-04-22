package org.example.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Getter;
import org.example.entity.Role;
import org.example.repository.impl.AbstractRepository;
import org.springframework.stereotype.Repository;

@Repository
public class RoleRepository extends AbstractRepository<Role, Long> {


    public RoleRepository(EntityManager entityManager, CriteriaBuilder criteriaBuilder) {
        super(entityManager, Role.class, criteriaBuilder);
    }

    @Override
    public Role findById(Long id){
        TypedQuery<Role> userTypedQuery = entityManager.createQuery("SELECT r  FROM Role r WHERE r.id = :id", Role.class)
                .setParameter("id", id);
        return userTypedQuery.getSingleResult();
    }
}
