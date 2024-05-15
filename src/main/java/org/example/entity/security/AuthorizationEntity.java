package org.example.entity.security;

import lombok.Getter;
import org.example.entity.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class AuthorizationEntity implements UserDetails {

    private final Long id;

    private final List<SimpleGrantedAuthority> authorities;

    private final String password;

    private final String username;

    public AuthorizationEntity(User user) {
        id = user.getId();
        authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().toString()))
                .collect(Collectors.toList());
        password = user.getCredentials().getPassword();
        username = user.getCredentials().getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
