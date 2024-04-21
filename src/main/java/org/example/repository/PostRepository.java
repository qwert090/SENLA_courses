package org.example.repository;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Getter;
import org.example.entity.Post;
import org.springframework.stereotype.Repository;

@Repository
@Getter
public class PostRepository extends AbstractRepository<Post, Long> {

    public PostRepository(EntityManager entityManager, CriteriaBuilder criteriaBuilder) {
        super(entityManager, Post.class, criteriaBuilder);
    }

    @Override
    public Post findById(Long id){
        EntityGraph<?> postGraph = entityManager.createEntityGraph("postGraph");
        TypedQuery<Post> typedQuery = entityManager.createQuery("SELECT p FROM Post p", Post.class);
        typedQuery.setHint("jakarta.persistence.fetchgraph", postGraph);
        return typedQuery.getSingleResult();
    }
}
