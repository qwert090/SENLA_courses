package org.example.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.dto.UserDto;
import org.example.entity.User;
import org.example.repository.impl.UserRepository;
import org.example.service.serviceInterface.UserService;
import org.example.utils.CustomMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final CustomMapper mapper;

    @Override
    @Transactional
    public void createUser(UserDto userDto) {
        User user = mapper.toEntity(User.class, userDto);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public UserDto getById(long id) {
        User user = userRepository.findById(id);
        return mapper.toDto(UserDto.class, user);
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void updateUser(UserDto userDto){
        userRepository.findById(userDto.getId());
        User updateUser = mapper.toEntity(User.class, userDto);
        userRepository.update(updateUser);
    }
}
