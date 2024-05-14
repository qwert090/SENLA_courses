package org.example.service.security;

import lombok.RequiredArgsConstructor;
import org.example.entity.User;
import org.example.entity.security.AuthorizationEntity;
import org.example.exception.entityNotFound.UserNotFoundException;
import org.example.repository.impl.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username).orElseThrow(() -> new UserNotFoundException("email " + username));
        AuthorizationEntity authorizationEntity = new AuthorizationEntity(user);
        return authorizationEntity;
    }
}
