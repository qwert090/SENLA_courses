package org.example.repository;

import lombok.Getter;
import org.example.entity.Role;
import org.example.entity.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Getter
public class RoleRepository {

    private List<Role> roles = new ArrayList<>();

    public void create(Role role){
        roles.add(role);
    }

    public Optional<Role> read(long roleId){
        Optional<Role> readRole = roles.stream()
                .filter(id -> id.getId() == roleId)
                .findFirst();
        return readRole;
    }

    public void delete(long roleId){
        roles = roles.stream()
                .filter(role -> role.getId() != roleId)
                .toList();
    }

    public void update(Role updateRole){
        roles = roles.stream()
                .filter(role -> role.getId() == updateRole.getId())
                .map(role -> updateRole)
                .toList();
    }
}
