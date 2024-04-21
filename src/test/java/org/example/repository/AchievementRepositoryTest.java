package org.example.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.transaction.Transactional;
import org.example.config.ApplicationConfigTest;
import org.example.entity.Achievement;
import org.example.entity.Game;
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
public class AchievementRepositoryTest {

    private AchievementRepository achievementRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private CriteriaBuilder criteriaBuilder;

    @BeforeEach
    public void setUp() {
        achievementRepository = new AchievementRepository(entityManager, criteriaBuilder);
    }

    @Test
    public void findAllTest() {
        List<Achievement> achievements = achievementRepository.findAll();
        assertNotNull(achievements);
        assertEquals(1, achievements.size());
    }

    @Test
    public void findByIdTest() {
        long id = 1L;
        Achievement achievement = achievementRepository.findById(id);
        assertNotNull(achievement);
        assertEquals("First Win", achievement.getName());
    }

    @Test
    @Transactional
    public void saveTest() {
        Achievement achievement = new Achievement();
        achievement.setName("AchievementName");
        long savedEntityId = achievementRepository.save(achievement);
        assertEquals(2L, savedEntityId);
        assertEquals("AchievementName", achievement.getName());
    }

    @Test
    @Transactional
    public void updateTest() {
        Achievement achievement = new Achievement();
        Game game = new Game();
        game.setId(1L);
        achievement.setId(1L);
        achievement.setName("UpdatedAchievement");
        achievement.setGame(game);
        achievementRepository.update(achievement);
        Achievement updatedAchievement = achievementRepository.findById(1L);
        assertNotNull(updatedAchievement);
        assertEquals("UpdatedAchievement", updatedAchievement.getName());
    }

    @Test
    @Transactional
    public void deleteTest() {
        Long achievementId = 1L;
        achievementRepository.deleteById(achievementId);
        assertThrows(NoResultException.class, () -> achievementRepository.findById(achievementId));
    }
}
