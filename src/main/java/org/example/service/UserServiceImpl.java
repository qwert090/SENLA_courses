package org.example.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.UserDto;
import org.example.entity.Credentials;
import org.example.entity.User;
import org.example.exception.EntityNotFoundException;
import org.example.repository.impl.UserRepository;
import org.example.service.serviceInterface.UserService;
import org.example.utils.CustomMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
        user.setCredentials(credentials);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public UserDto getById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
        return mapper.toDto(UserDto.class, user);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void updateUser(UserDto userDto){
        User updateUser = mapper.toEntity(User.class, userDto);
        userRepository.update(updateUser);
    }
}
