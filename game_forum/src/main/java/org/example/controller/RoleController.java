package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.RoleDto;
import org.example.service.serviceInterface.RoleService;
import org.example.utils.Json;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;
    private final Json json;

    public void createRole(String serializedRole){
        RoleDto roleDto = json.deserialize(serializedRole, RoleDto.class);
        roleService.createRole(roleDto);
    }

    public void deleteById(long roleId){
        roleService.deleteById(roleId);
    }

    public void updateRole(String serializedRole){
        RoleDto roleDto = json.deserialize(serializedRole, RoleDto.class);
        roleService.updateRole(roleDto);
    }

    public RoleDto getById(long roleId){
        return roleService.getById(roleId);
    }
}
