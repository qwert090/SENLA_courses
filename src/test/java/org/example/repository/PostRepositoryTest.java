package org.example.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.transaction.Transactional;
import org.example.config.ApplicationConfigTest;
import org.example.entity.Post;
import org.example.repository.impl.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration(classes = {ApplicationConfigTest.class})
@ExtendWith(SpringExtension.class)
public class PostRepositoryTest {
    private PostRepository postRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private CriteriaBuilder criteriaBuilder;

    @BeforeEach
    public void setUp() {
        postRepository = new PostRepository(entityManager, criteriaBuilder);
    }

    @Test
    public void findAllTest() {
        List<Post> posts = postRepository.findAll();
        assertNotNull(posts);
        assertEquals(1, posts.size());
    }

    @Test
    public void findByIdTest() {
        Long id = 1L;
        Post post = postRepository.findById(id);
        assertNotNull(post);
        assertEquals("Hello, world!", post.getContent());
    }

    @Test
    @Transactional
    public void saveTest() {
        Post post = new Post();
        post.setContent("PostTitle");
        assertEquals("PostTitle", post.getContent());
    }

    @Test
    @Transactional
    public void updateTest() {
        Post post = new Post();
        post.setId(1L);
        post.setContent("UpdatePost");
        postRepository.update(post);
        Post updatedPost = postRepository.findById(1L);
        assertNotNull(updatedPost);
        assertEquals("UpdatePost", updatedPost.getContent());
    }

    @Test
    @Transactional
    public void deleteTest() {
        Long postId = 1L;
        postRepository.deleteById(postId);
        assertThrows(NoResultException.class, () -> postRepository.findById(postId));
    }
}
