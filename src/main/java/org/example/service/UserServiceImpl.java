package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.annotation.Transaction;
import org.example.dto.UserDto;
import org.example.entity.User;
import org.example.repository.UserRepository;
import org.example.service.serviceInterface.UserService;
import org.example.utils.CustomMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final CustomMapper mapper;

    @Override
    @Transaction
    public void createUser(UserDto userDto) {
        User user = mapper.toEntity(User.class, userDto);
        userRepository.create(user);
    }

    @Override
    @Transaction
    public UserDto getById(long id) {
        User user = userRepository.read(id);
        return mapper.toDto(UserDto.class, user);
    }

    @Override
    @Transaction
    public void deleteById(long id) {
        userRepository.delete(id);
    }

    @Override
    @Transaction
    public void updateUser(UserDto userDto){
        userRepository.read(userDto.getId());
        User updateUser = mapper.toEntity(User.class, userDto);
        userRepository.update(updateUser);
    }
}
