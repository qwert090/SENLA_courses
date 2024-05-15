package org.example.service;

import org.example.config.ApplicationConfigTest;
import org.example.dto.RoleDto;
import org.example.dto.UserDto;
import org.example.entity.User;
import org.example.repository.impl.UserRepository;
import org.example.utils.CustomMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {ApplicationConfigTest.class})
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private CustomMapper mapper;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void createUserTest() {
        UserDto userDto = new UserDto();
        RoleDto roleDto = new RoleDto();
        List<RoleDto> roleDtoList = new ArrayList<>();
        userDto.setRoles(roleDtoList);
        User user = new User();
        when(mapper.toEntity(User.class, userDto)).thenReturn(user);
        userService.createUser(userDto);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void getByIdTest() {
        long userId = 1L;
        User user = new User();
        UserDto userDto = new UserDto();
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(mapper.toDto(UserDto.class, user)).thenReturn(userDto);
        UserDto result = userService.getById(userId);
        assertSame(userDto, result);
    }

    @Test
    public void deleteByIdTest() {
        long userId = 1L;
        userService.deleteById(userId);
        verify(userRepository, times(1)).deleteById(userId);
    }

    @Test
    public void updateUserTest() {
        UserDto userDto = new UserDto();
        User updateUser = new User();
        when(mapper.toEntity(User.class, userDto)).thenReturn(updateUser);
        userService.updateUser(userDto);
        verify(userRepository, times(1)).update(updateUser);
    }
}
