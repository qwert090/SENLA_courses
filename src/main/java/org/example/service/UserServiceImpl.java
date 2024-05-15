package org.example.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.dto.UserDto;
import org.example.entity.Credentials;
import org.example.entity.Role;
import org.example.entity.User;
import org.example.exception.entityNotFound.UserNotFoundException;
import org.example.repository.impl.UserRepository;
import org.example.service.serviceInterface.UserService;
import org.example.utils.CustomMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final CustomMapper mapper;

    @Override
    @Transactional
    public void createUser(UserDto userDto) {
        User user = mapper.toEntity(User.class, userDto);
        Credentials credentials = mapper.toEntity(Credentials.class, userDto.getCredentials());
        List<Role> roles = userDto.getRoles().stream()
                .map(roleDto -> mapper.toEntity(Role.class, roleDto))
                .toList();
        user.setRoles(roles);
        user.setCredentials(credentials);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public UserDto getById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("id " + id));
        return mapper.toDto(UserDto.class, user);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void updateUser(UserDto userDto) {
        User updateUser = mapper.toEntity(User.class, userDto);
        userRepository.update(updateUser);
    }
}
