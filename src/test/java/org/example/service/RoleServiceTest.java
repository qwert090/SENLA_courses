package org.example.service;

import org.example.config.ApplicationConfigTest;
import org.example.dto.RoleDto;
import org.example.entity.Role;
import org.example.repository.impl.RoleRepository;
import org.example.utils.CustomMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {ApplicationConfigTest.class})
@ExtendWith(MockitoExtension.class)
public class RoleServiceTest {

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private CustomMapper mapper;

    @InjectMocks
    private RoleServiceImpl roleService;

    @Test
    public void createRoleTest() {
        Role role = new Role();
        RoleDto roleDto = new RoleDto();
        when(mapper.toEntity(Role.class, roleDto)).thenReturn(role);
        roleService.createRole(roleDto);
        verify(roleRepository, times(1)).save(role);
    }

    @Test
    public void updateRoleTest() {
        RoleDto roleDto = new RoleDto();
        Role role = new Role();
        when(mapper.toEntity(Role.class, roleDto)).thenReturn(role);
        roleService.updateRole(roleDto);
        verify(roleRepository, times(1)).update(role);
    }

    @Test
    public void getByIdTest() {
        RoleDto roleDto = new RoleDto();
        Role role = new Role();
        long roleId = 1L;
        when(roleRepository.findById(roleId)).thenReturn(role);
        when(mapper.toDto(RoleDto.class, role)).thenReturn(roleDto);
        RoleDto result = roleService.getById(roleId);
        assertSame(roleDto, result);
    }

    @Test
    public void deleteByIdTest() {
        long roleId = 1L;
        roleService.deleteById(roleId);
        verify(roleRepository, times(1)).deleteById(roleId);
    }
}
