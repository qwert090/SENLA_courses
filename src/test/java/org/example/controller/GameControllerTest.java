package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.example.config.ApplicationConfigTest;
import org.example.dto.GameDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = {ApplicationConfigTest.class})
@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@Transactional
public class GameControllerTest {
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void testCreateGame() throws Exception {
        GameDto gameDto = new GameDto();
        gameDto.setName("My Game");

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/games")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(gameDto));
        mockMvc.perform(requestBuilder)
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void testGetGameById() throws Exception {
        Long gameId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.get("/games/{gameId}", gameId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testDeleteGameById() throws Exception {
        Long gameId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.delete("/games/{gameId}", gameId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testUpdateGame() throws Exception {
        GameDto gameDto = new GameDto();
        gameDto.setId(1L);
        gameDto.setName("Updated Game");

        mockMvc.perform(MockMvcRequestBuilders.put("/games")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(gameDto)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
