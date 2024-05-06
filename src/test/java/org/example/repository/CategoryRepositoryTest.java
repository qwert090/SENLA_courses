package org.example.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.transaction.Transactional;
import org.example.config.ApplicationConfigTest;
import org.example.entity.Category;
import org.example.repository.impl.CategoryRepository;
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
public class CategoryRepositoryTest {
    private CategoryRepository categoryRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private CriteriaBuilder criteriaBuilder;

    @BeforeEach
    public void setUp() {
        categoryRepository = new CategoryRepository(entityManager, criteriaBuilder);
    }

    @Test
    public void findAllTest() {
        List<Category> categories = categoryRepository.findAll();
        assertNotNull(categories);
        assertEquals(1, categories.size());
    }

    @Test
    public void findByIdTest() {
        Long id = 1L;
        Category category = categoryRepository.findById(id).orElseThrow();
        assertNotNull(category);
        assertEquals("Technology", category.getName());
    }

    @Test
    @Transactional
    public void saveTest() {
        Category category = new Category();
        category.setName("CategoryName");
        assertEquals("CategoryName", category.getName());
    }

    @Test
    @Transactional
    public void updateTest() {
        Category category = new Category();
        category.setId(1L);
        category.setName("UpdatedCategory");
        categoryRepository.update(category);
        Category updatedCategory = categoryRepository.findById(1L).orElseThrow();
        assertNotNull(updatedCategory);
        assertEquals("UpdatedCategory", updatedCategory.getName());
    }

    @Test
    @Transactional
    public void deleteTest() {
        Long categoryId = 1L;
        categoryRepository.deleteById(categoryId);
        assertThrows(NoResultException.class, () -> categoryRepository.findById(categoryId));
    }
}
