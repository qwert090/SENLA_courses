package org.example.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.transaction.Transactional;
import org.example.config.ApplicationConfigTest;
import org.example.entity.Comments;
import org.example.entity.User;
import org.example.repository.impl.CommentRepository;
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
public class CommentRepositoryTest {

    private CommentRepository commentRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private CriteriaBuilder criteriaBuilder;

    @BeforeEach
    public void setUp() {
        commentRepository = new CommentRepository(entityManager, criteriaBuilder);
    }

    @Test
    public void findAllTest() {
        List<Comments> comments = commentRepository.findAll();
        assertNotNull(comments);
        assertEquals(1, comments.size());
    }

    @Test
    public void findByIdTest() {
        Long id = 1L;
        Comments comment = commentRepository.findById(id);
        assertNotNull(comment);
        assertEquals("Great post!", comment.getContent());
    }

    @Test
    @Transactional
    public void saveTest() {
        Comments comment = new Comments();
        comment.setContent("CommentContent");
        assertEquals("CommentContent", comment.getContent());
    }

    @Test
    @Transactional
    public void updateTest() {
        Comments comment = new Comments();
        User user = new User();
        user.setId(1L);
        comment.setId(1L);
        comment.setContent("UpdatedComment");
        comment.setUser(user);
        commentRepository.update(comment);
        Comments updatedComment = commentRepository.findById(1L);
        assertNotNull(updatedComment);
        assertEquals("UpdatedComment", updatedComment.getContent());
    }

    @Test
    @Transactional
    public void deleteTest() {
        Long commentId = 1L;
        commentRepository.deleteById(commentId);
        assertThrows(NoResultException.class, () -> commentRepository.findById(commentId));
    }
}
