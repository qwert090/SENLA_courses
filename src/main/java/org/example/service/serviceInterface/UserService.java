package org.example.service.serviceInterface;

import org.example.dto.UserDto;

public interface UserService {

    void createUser(UserDto userDto);

    void deleteById(Long id);

    UserDto getById(Long id);

    void updateUser(UserDto userDto);
}
