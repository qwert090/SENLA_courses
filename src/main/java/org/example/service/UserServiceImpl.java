package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dto.UserDto;
import org.example.entity.User;
import org.example.exception.EntityNotFoundException;
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
    public void createUser(UserDto userDto) {
        User user = mapper.toEntity(User.class, userDto);
        userRepository.create(user);
    }

    @Override
    public UserDto getById(long id) {
        User user = userRepository.read(id)
                .orElseThrow(() -> new EntityNotFoundException("no such user"));
        System.out.println(userRepository.getUsers().stream()
                .filter(user1 -> user1.getId() == id)
                .toList()
        );
        return mapper.toDto(UserDto.class, user);
    }

    @Override
    public void deleteById(long id) {
        userRepository.delete(id);
    }

    @Override
    public void updateUser(UserDto userDto){
        userRepository.read(userDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("no such user"));
        User updateUser = mapper.toEntity(User.class, userDto);
        userRepository.update(updateUser);
    }
}
