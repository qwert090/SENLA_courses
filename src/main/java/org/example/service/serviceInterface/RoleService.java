package org.example.service.serviceInterface;

import org.example.dto.RoleDto;

public interface RoleService {

    void createRole(RoleDto roleDto);

    void deleteById(Long id);

    RoleDto getById(Long id);

    void updateRole(RoleDto roleDto);
}
