package org.example.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.transaction.Transactional;
import org.example.config.ApplicationConfigTest;
import org.example.entity.Credentials;
import org.example.repository.impl.CredentialsRepository;
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
public class CredentialsRepositoryTest {
    private CredentialsRepository credentialsRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private CriteriaBuilder criteriaBuilder;

    @BeforeEach
    public void setUp() {
        credentialsRepository = new CredentialsRepository(entityManager, criteriaBuilder);
    }

    @Test
    public void findAllTest() {
        List<Credentials> credentialsList = credentialsRepository.findAll();
        assertNotNull(credentialsList);
        assertEquals(1, credentialsList.size());
    }

    @Test
    public void findByIdTest() {
        Long id = 1L;
        Credentials credentials = credentialsRepository.findById(id);
        assertNotNull(credentials);
        assertEquals("password123", credentials.getPassword());
    }

    @Test
    @Transactional
    public void saveTest() {
        Credentials credentials = new Credentials();
        credentials.setPassword("Password");
        assertEquals("Password", credentials.getPassword());
    }

    @Test
    @Transactional
    public void updateTest() {
        Credentials credentials = new Credentials();
        credentials.setId(1L);
        credentials.setPassword("UpdatedPassword");
        credentialsRepository.update(credentials);
        Credentials updatedCredentials = credentialsRepository.findById(1L);
        assertNotNull(updatedCredentials);
        assertEquals("UpdatedPassword", updatedCredentials.getPassword());
    }

    @Test
    @Transactional
    public void deleteTest() {
        Long credentialsId = 1L;
        credentialsRepository.deleteById(credentialsId);
        assertThrows(NoResultException.class, () -> credentialsRepository.findById(credentialsId));
    }
}
