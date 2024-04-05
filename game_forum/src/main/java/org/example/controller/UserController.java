package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.UserDto;
import org.example.service.serviceInterface.UserService;
import org.example.utils.Json;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final Json json;

    public void createUser(String serializedUser){
        UserDto userDto = json.deserialize(serializedUser, UserDto.class);
        userService.createUser(userDto);
    }

    public void deleteById(long userId){
        userService.deleteById(userId);
    }

    public void updateUser(String serializedUser){
        UserDto userDto = json.deserialize(serializedUser, UserDto.class);
        userService.updateUser(userDto);
    }

    public UserDto getById(long userId){
        return userService.getById(userId);
    }
}
