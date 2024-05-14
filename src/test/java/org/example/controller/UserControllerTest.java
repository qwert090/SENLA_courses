package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.example.config.ApplicationConfigTest;
import org.example.config.JwtAuthenticationFilter;
import org.example.controller.security.AuthenticationRequest;
import org.example.dto.CredentialsDto;
import org.example.dto.RoleDto;
import org.example.dto.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = {ApplicationConfigTest.class})
@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@Transactional
public class UserControllerTest {

    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;
    private String token;

    @BeforeEach
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .addFilter(jwtAuthenticationFilter)
                .build();
        AuthenticationRequest requestDto = new AuthenticationRequest("john.doe@example.com", "password123");
        String json = objectMapper.writeValueAsString(requestDto);
        var requestBuilder = MockMvcRequestBuilders.post("/auth/authenticate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);
        MvcResult mvcResult = mockMvc.perform(requestBuilder)
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        token = "Bearer " + mvcResult.getResponse().getContentAsString()
                .split(":")[1]
                .replace('\"', ' ')
                .replace('}', ' ')
                .trim();
    }

    @Test
    public void testCreateUser() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setNickname("John Doe");
        CredentialsDto credentialsDto = new CredentialsDto();
        RoleDto roleDto = new RoleDto();
        List<RoleDto> roleDtoList = new ArrayList<>();
        roleDtoList.add(roleDto);
        userDto.setRoles(roleDtoList);
        userDto.setCredentials(credentialsDto);
        String userJson = objectMapper.writeValueAsString(userDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .header("Authorization", token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void testGetUserById() throws Exception {
        Long userId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.get("/users/{userId}", userId)
                        .header("Authorization", token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nickname").value("john_doe"));
    }

    @Test
    public void testUpdateUser() throws Exception {
        UserDto userDto = new UserDto();
        CredentialsDto credentialsDto = new CredentialsDto();
        userDto.setCredentials(credentialsDto);
        userDto.setId(1L);

        mockMvc.perform(MockMvcRequestBuilders.put("/users")
                        .header("Authorization", token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    public void testDeleteUserById() throws Exception {
        Long userId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.delete("/users/{userId}", userId)
                        .header("Authorization", token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}


