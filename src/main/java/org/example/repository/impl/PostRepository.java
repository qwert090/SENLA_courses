package org.example.repository.impl;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.example.entity.Post;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class PostRepository extends AbstractRepository<Post, Long> {

    public PostRepository(EntityManager entityManager, CriteriaBuilder criteriaBuilder) {
        super(entityManager, Post.class, criteriaBuilder);
    }

    @Override
    public Optional<Post> findById(Long id) {
        EntityGraph<?> postGraph = entityManager.createEntityGraph("postGraph");
        TypedQuery<Post> typedQuery = entityManager.createQuery("SELECT p FROM Post p WHERE p.id = :id", Post.class);
        typedQuery.setParameter("id", id);
        typedQuery.setHint("javax.persistence.fetchgraph", postGraph);
        return Optional.ofNullable(typedQuery.getSingleResult());
    }
}
