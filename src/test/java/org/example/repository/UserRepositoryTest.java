package org.example.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.transaction.Transactional;
import org.example.config.ApplicationConfigTest;
import org.example.entity.Credentials;
import org.example.entity.User;
import org.example.repository.impl.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration(classes = {ApplicationConfigTest.class})
@ExtendWith(SpringExtension.class)
@WebAppConfiguration
public class UserRepositoryTest {

    private UserRepository userRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private CriteriaBuilder criteriaBuilder;

    @BeforeEach
    public void setUp() {
        userRepository = new UserRepository(entityManager, criteriaBuilder);
    }

    @Test
    public void findAllTest() {
        List<User> users = userRepository.findAll();
        assertNotNull(users);
        assertEquals(1, users.size());
    }

    @Test
    public void findByIdTest() {
        Long id = 1L;
        User user = userRepository.findById(id);
        assertNotNull(user);
        assertEquals("john_doe", user.getNickname());
    }

    @Test
    @Transactional
    public void saveTest() {
        User user = new User();
        user.setNickname("UserName");
        Credentials credentials = new Credentials();
        user.setCredentials(credentials);
        assertEquals("UserName", user.getNickname());
    }

    @Test
    @Transactional
    public void updateTest() {
        User user = new User();
        user.setId(1L);
        user.setNickname("john_doe");
        Credentials credentials = new Credentials();
        user.setCredentials(credentials);
        userRepository.update(user);
        User updatedUser = userRepository.findById(1L);
        assertNotNull(updatedUser);
        assertEquals("john_doe", updatedUser.getNickname());
    }

    @Test
    @Transactional
    public void deleteTest() {
        Long userId = 1L;
        userRepository.deleteById(userId);
        assertThrows(NoResultException.class, () -> userRepository.findById(userId));
    }
}
