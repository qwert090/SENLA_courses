package org.example.service.serviceInterface;

import org.example.dto.UserDto;

public interface UserService {

    void createUser(UserDto userDto);

    void deleteById(long id);

    UserDto getById(long id);

    void updateUser(UserDto userDto);
}
