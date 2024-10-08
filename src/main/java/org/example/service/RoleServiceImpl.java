package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dto.RoleDto;
import org.example.entity.Role;
import org.example.exception.entityNotFound.RoleNotFoundException;
import org.example.repository.impl.RoleRepository;
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
        roleRepository.save(role);
    }

    @Override
    public void deleteById(Long id) {
        roleRepository.deleteById(id);
    }

    @Override
    public RoleDto getById(Long id) {
        Role role = roleRepository.findById(id).orElseThrow(() -> new RoleNotFoundException("id " + id));
        return mapper.toDto(RoleDto.class, role);
    }

    @Override
    public void updateRole(RoleDto roleDto) {
        Role updateRole = mapper.toEntity(Role.class, roleDto);
        roleRepository.update(updateRole);
    }
}
