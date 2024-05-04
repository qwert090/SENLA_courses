package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.RoleDto;
import org.example.service.serviceInterface.RoleService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;

    @PostMapping
    public void createRole(@RequestBody RoleDto roleDto){
        roleService.createRole(roleDto);
    }

    @DeleteMapping("/{roleId}")
    public void deleteById(@PathVariable("roleId") Long roleId){
        roleService.deleteById(roleId);
    }

    @PutMapping
    public void updateRole(@RequestBody RoleDto roleDto){
        roleService.updateRole(roleDto);
    }

    @GetMapping("/{roleId}")
    public RoleDto getById(@PathVariable("roleId") Long roleId){
        return roleService.getById(roleId);
    }
}
