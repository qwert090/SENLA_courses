package org.example.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.transaction.Transactional;
import org.example.config.ApplicationConfigTest;
import org.example.entity.Role;
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
public class RoleRepositoryTest {
    private RoleRepository roleRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private CriteriaBuilder criteriaBuilder;

    @BeforeEach
    public void setUp() {
        roleRepository = new RoleRepository(entityManager, criteriaBuilder);
    }

    @Test
    public void findAllTest() {
        List<Role> roles = roleRepository.findAll();
        assertNotNull(roles);
        assertEquals(1, roles.size());
    }

    @Test
    public void findByIdTest() {
        Long id = 1L;
        Role role = roleRepository.findById(id);
        assertNotNull(role);
        assertEquals("Admin", role.getName());
    }

    @Test
    @Transactional
    public void saveTest() {
        Role role = new Role();
        role.setName("RoleName");
        long savedEntityId = roleRepository.save(role);
        assertEquals(2L, savedEntityId);
        assertEquals("RoleName", role.getName());
    }

    @Test
    @Transactional
    public void updateTest() {
        Role role = new Role();
        role.setId(1L);
        role.setName("UpdateRole");
        roleRepository.update(role);
        Role updatedRole = roleRepository.findById(1L);
        assertNotNull(updatedRole);
        assertEquals("UpdateRole", updatedRole.getName());
    }

    @Test
    @Transactional
    public void deleteTest() {
        Long roleId = 1L;
        roleRepository.deleteById(roleId);
        assertThrows(NoResultException.class, () -> roleRepository.findById(roleId));
    }
}
