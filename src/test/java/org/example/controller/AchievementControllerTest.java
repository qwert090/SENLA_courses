package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.example.config.ApplicationConfigTest;
import org.example.config.JwtAuthenticationFilter;
import org.example.controller.security.AuthenticationRequest;
import org.example.dto.AchievementDto;
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
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@ContextConfiguration(classes = {ApplicationConfigTest.class})
@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@Transactional
public class AchievementControllerTest {
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
    public void testCreateAchievement() throws Exception {
        AchievementDto achievementDto = new AchievementDto();
        achievementDto.setName("My Achievement");

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/achievements")
                .header("Authorization", token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(achievementDto));
        mockMvc.perform(requestBuilder)
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void testGetAchievementById() throws Exception {
        Long achievementId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.get("/achievements/{achievementId}", achievementId)
                        .header("Authorization", token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("First Win"));
    }

    @Test
    public void testDeleteAchievementById() throws Exception {
        Long achievementId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.delete("/achievements/{achievementId}", achievementId)
                        .header("Authorization", token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testUpdateAchievement() throws Exception {
        AchievementDto achievementDto = new AchievementDto();
        achievementDto.setId(1L);
        achievementDto.setName("Updated Achievement");

        mockMvc.perform(MockMvcRequestBuilders.put("/achievements")
                        .header("Authorization", token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(achievementDto)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
