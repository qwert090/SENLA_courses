package org.example.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.Getter;
import org.example.entity.Users;
import org.springframework.stereotype.Repository;

@Repository
@Getter
public class UserRepository extends AbstractRepository<Users, Long> {

    public UserRepository(EntityManager entityManager, CriteriaBuilder criteriaBuilder) {
        super(entityManager, Users.class, criteriaBuilder);
    }

    @Override
    public Users findById(Long id) {
        CriteriaQuery<Users> criteriaQuery = criteriaBuilder.createQuery(Users.class);
        Root<Users> root = criteriaQuery.from(Users.class);
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("id"), id));
        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }
}
