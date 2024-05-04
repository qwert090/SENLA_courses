package org.example.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.transaction.Transactional;
import org.example.config.ApplicationConfigTest;
import org.example.entity.Game;
import org.example.repository.impl.GameRepository;
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
public class GameRepositoryTest {
    private GameRepository gameRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private CriteriaBuilder criteriaBuilder;

    @BeforeEach
    public void setUp() {
        gameRepository = new GameRepository(entityManager, criteriaBuilder);
    }

    @Test
    public void findAllTest() {
        List<Game> games = gameRepository.findAll();
        assertNotNull(games);
        assertEquals(1, games.size());
    }

    @Test
    public void findByIdTest() {
        Long id = 1L;
        Game game = gameRepository.findById(id);
        assertNotNull(game);
        assertEquals("Chess", game.getName());
    }

    @Test
    @Transactional
    public void saveTest() {
        Game game = new Game();
        game.setName("GameTitle");
        assertEquals("GameTitle", game.getName());
    }

    @Test
    @Transactional
    public void updateTest() {
        Game game = new Game();
        game.setId(1L);
        game.setName("UpdateGame");
        gameRepository.update(game);
        Game updatedGame = gameRepository.findById(1L);
        assertNotNull(updatedGame);
        assertEquals("UpdateGame", updatedGame.getName());
    }

    @Test
    @Transactional
    public void deleteTest() {
        Long gameId = 1L;
        gameRepository.deleteById(gameId);
        assertThrows(NoResultException.class, () -> gameRepository.findById(gameId));
    }
}
