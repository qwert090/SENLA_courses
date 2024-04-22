package org.example.repository.impl;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Getter;
import org.example.entity.Comments;
import org.example.repository.impl.AbstractRepository;
import org.springframework.stereotype.Repository;

@Repository
public class CommentRepository extends AbstractRepository<Comments, Long> {

    public CommentRepository(EntityManager entityManager, CriteriaBuilder criteriaBuilder) {
        super(entityManager, Comments.class, criteriaBuilder);
    }

    @Override
    public Comments findById(Long id) {
        EntityGraph<Comments> entityGraph = entityManager.createEntityGraph(Comments.class);
        TypedQuery<Comments> typedQuery = entityManager.createQuery("SELECT c FROM Comments c WHERE c.id = :id", Comments.class);
        typedQuery.setParameter("id", id);
        entityGraph.addAttributeNodes("user", "post");
        typedQuery.setHint("jakarta.persistence.loadgraph", entityGraph);
        return typedQuery.getSingleResult();
    }
}
