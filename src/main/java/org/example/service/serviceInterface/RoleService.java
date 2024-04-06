package org.example.service.serviceInterface;

import org.example.dto.RoleDto;

public interface RoleService {

    void createRole(RoleDto roleDto);

    void deleteById(long id);

    RoleDto getById(long id);

    void updateRole(RoleDto roleDto);
}
