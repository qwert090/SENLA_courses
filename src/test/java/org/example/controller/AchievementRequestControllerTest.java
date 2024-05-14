package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.example.config.ApplicationConfigTest;
import org.example.config.JwtAuthenticationFilter;
import org.example.controller.security.AuthenticationRequest;
import org.example.dto.AchievementRequestDto;
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
public class AchievementRequestControllerTest {
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
    public void testCreateAchievementRequest() throws Exception {
        AchievementRequestDto achievementRequestDto = new AchievementRequestDto();
        achievementRequestDto.setAchievementName("My Achievement Request");

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/achievementRequests")
                .header("Authorization", token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(achievementRequestDto));
        mockMvc.perform(requestBuilder)
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void testGetAchievementRequestById() throws Exception {
        Long achievementRequestId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.get("/achievementRequests/{achievementRequestId}", achievementRequestId)
                        .header("Authorization", token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.achievementName").value("Grandmaster"));
    }

    @Test
    public void testDeleteAchievementRequestById() throws Exception {
        Long achievementRequestId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.delete("/achievementRequests/{achievementRequestId}", achievementRequestId)
                        .header("Authorization", token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testUpdateAchievementRequest() throws Exception {
        AchievementRequestDto achievementRequestDto = new AchievementRequestDto();
        achievementRequestDto.setId(1L);
        achievementRequestDto.setAchievementName("Updated Achievement Request");

        mockMvc.perform(MockMvcRequestBuilders.put("/achievementRequests")
                        .header("Authorization", token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(achievementRequestDto)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
