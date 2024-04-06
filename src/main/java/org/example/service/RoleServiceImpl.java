package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dto.RoleDto;
import org.example.entity.Role;
import org.example.exception.EntityNotFoundException;
import org.example.repository.RoleRepository;
import org.example.service.serviceInterface.RoleService;
import org.example.utils.CustomMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final CustomMapper mapper;

    @Override
    public void createRole(RoleDto roleDto) {
        Role role = mapper.toEntity(Role.class, roleDto);
        roleRepository.create(role);
    }

    @Override
    public void deleteById(long id) {
        roleRepository.delete(id);
    }

    @Override
    public RoleDto getById(long id) {
        Role role = roleRepository.read(id)
                .orElseThrow(() -> new EntityNotFoundException("no such role"));
        System.out.println(roleRepository.getRoles().stream()
                .filter(role1 -> role1.getId() == id)
                .toList()
        );
        return mapper.toDto(RoleDto.class, role);
    }

    @Override
    public void updateRole(RoleDto roleDto) {
        roleRepository.read(roleDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("no such role"));
        Role updateRole = mapper.toEntity(Role.class, roleDto);
        roleRepository.update(updateRole);
    }
}
