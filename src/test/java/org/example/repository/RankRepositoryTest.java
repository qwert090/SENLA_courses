package org.example.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.transaction.Transactional;
import org.example.config.ApplicationConfigTest;
import org.example.entity.Rank;
import org.example.repository.impl.RankRepository;
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
public class RankRepositoryTest {
    private RankRepository rankRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private CriteriaBuilder criteriaBuilder;

    @BeforeEach
    public void setUp() {
        rankRepository = new RankRepository(entityManager, criteriaBuilder);
    }

    @Test
    public void findAllTest() {
        List<Rank> ranks = rankRepository.findAll();
        assertNotNull(ranks);
        assertEquals(1, ranks.size());
    }

    @Test
    public void findByIdTest() {
        Long id = 1L;
        Rank rank = rankRepository.findById(id);
        assertNotNull(rank);
        assertEquals("Beginner", rank.getName());
    }

    @Test
    @Transactional
    public void saveTest() {
        Rank rank = new Rank();
        rank.setName("RankName");
        assertEquals("RankName", rank.getName());
    }

    @Test
    @Transactional
    public void updateTest() {
        Rank rank = new Rank();
        rank.setId(1L);
        rank.setName("UpdateRank");
        rankRepository.update(rank);
        Rank updatedRank = rankRepository.findById(1L);
        assertNotNull(updatedRank);
        assertEquals("UpdateRank", updatedRank.getName());
    }

    @Test
    @Transactional
    public void deleteTest() {
        Long rankId = 1L;
        rankRepository.deleteById(rankId);
        assertThrows(NoResultException.class, () -> rankRepository.findById(rankId));
    }
}
