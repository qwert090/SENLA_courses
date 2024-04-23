package org.example.repository.impl;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.example.entity.Comment;
import org.springframework.stereotype.Repository;

@Repository
public class CommentRepository extends AbstractRepository<Comment, Long> {

    public CommentRepository(EntityManager entityManager, CriteriaBuilder criteriaBuilder) {
        super(entityManager, Comment.class, criteriaBuilder);
    }

    @Override
    public Comment findById(Long id) {
        EntityGraph<Comment> entityGraph = entityManager.createEntityGraph(Comment.class);
        TypedQuery<Comment> typedQuery = entityManager.createQuery("SELECT c FROM Comment c WHERE c.id = :id", Comment.class);
        typedQuery.setParameter("id", id);
        entityGraph.addAttributeNodes("user", "post");
        typedQuery.setHint("jakarta.persistence.loadgraph", entityGraph);
        return typedQuery.getSingleResult();
    }
}
