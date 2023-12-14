package com.fiadotau.playermanager.controller;

import com.fiadotau.playermanager.facade.controller.PlayerController;
import com.fiadotau.playermanager.model.domain.Player;
import com.fiadotau.playermanager.model.service.PlayerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PlayerController.class)
public class PlayerControllerTest {

    private MockMvc mockMvc;

    @MockBean
    private PlayerService playerService;

    @Autowired
    private PlayerController playerController;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(playerController).build();
    }

    /* TODO
    If I had an infinite amount of time, it's worth to create a dedicated 'TestDataProvider' utility class to organize and manage test data.
    This approach would streamline the test setup process and improve code reusability across different test cases.*/
    @Test
    public void testGetAllPlayers() throws Exception {
        //Given
        String playerId1 = "aardsda01";
        String playerId2 = "aaronha01";

        Player player1 = new Player();
        player1.setPlayerId(playerId1);

        Player player2 = new Player();
        player2.setPlayerId(playerId2);

        int page = 0;
        int size = 10;

        Page<Player> pagedResponse = new PageImpl<>(Arrays.asList(player1, player2), PageRequest.of(page, size), 2);

        given(playerService.getAllPlayers(page, size)).willReturn(pagedResponse);

        //When
        mockMvc.perform(get("/api/players")
                        .param("page", String.valueOf(page))
                        .param("size", String.valueOf(size))
                        .contentType(MediaType.APPLICATION_JSON))
                //Then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].playerId", is(playerId1)))
                .andExpect(jsonPath("$.content[1].playerId", is(playerId2)))
                .andExpect(jsonPath("$.totalElements", is(2)))
                .andExpect(jsonPath("$.totalPages", is(1)))
                .andExpect(jsonPath("$.number", is(0)));
    }

    @Test
    public void testGetPlayerById() throws Exception {
        //Given
        String playerId = "aardsda01";

        Player player1 = new Player();
        player1.setPlayerId(playerId);

        given(playerService.getPlayerById(playerId)).willReturn(Optional.of(player1));

        //When
        mockMvc.perform(get("/api/players/" + playerId))
                //Then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.playerId", is(playerId)));
    }
}