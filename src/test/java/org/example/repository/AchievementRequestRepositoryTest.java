package org.example.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.transaction.Transactional;
import org.example.config.ApplicationConfigTest;
import org.example.entity.AchievementRequest;
import org.example.repository.impl.AchievementRequestRepository;
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
public class AchievementRequestRepositoryTest {
    private AchievementRequestRepository achievementRequestRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private CriteriaBuilder criteriaBuilder;

    @BeforeEach
    public void setUp() {
        achievementRequestRepository = new AchievementRequestRepository(entityManager, criteriaBuilder);
    }

    @Test
    public void findAllTest() {
        List<AchievementRequest> achievementRequests = achievementRequestRepository.findAll();
        assertNotNull(achievementRequests);
        assertEquals(1, achievementRequests.size());
    }

    @Test
    public void findByIdTest() {
        Long id = 1L;
        AchievementRequest achievementRequest = achievementRequestRepository.findById(id);
        assertNotNull(achievementRequest);
        assertEquals("Grandmaster", achievementRequest.getAchievementName());
    }

    @Test
    @Transactional
    public void saveTest() {
        AchievementRequest achievementRequest = new AchievementRequest();
        achievementRequest.setAchievementName("RequestName");
        assertEquals("RequestName", achievementRequest.getAchievementName());
    }

    @Test
    @Transactional
    public void updateTest() {
        AchievementRequest achievementRequest = new AchievementRequest();
        achievementRequest.setId(1L);
        achievementRequest.setAchievementName("UpdatedRequest");
        achievementRequestRepository.update(achievementRequest);
        AchievementRequest updatedRequest = achievementRequestRepository.findById(1L);
        assertNotNull(updatedRequest);
        assertEquals("UpdatedRequest", updatedRequest.getAchievementName());
    }

    @Test
    @Transactional
    public void deleteTest() {
        Long requestId = 1L;
        achievementRequestRepository.deleteById(requestId);
        assertThrows(NoResultException.class, () -> achievementRequestRepository.findById(requestId));
    }
}
