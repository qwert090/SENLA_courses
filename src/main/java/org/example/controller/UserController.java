package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.UserDto;
import org.example.service.serviceInterface.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public void createUser(@RequestBody UserDto userDto){
        userService.createUser(userDto);
    }

    @DeleteMapping("/{userId}")
    public void deleteById(@PathVariable("userId") Long userId){
        userService.deleteById(userId);
    }

    @PutMapping
    public void updateUser(@RequestBody UserDto userDto){
        userService.updateUser(userDto);
    }

    @GetMapping("/{userId}")
    public UserDto getById(@PathVariable("userId") Long userId){
        return userService.getById(userId);
    }
}
