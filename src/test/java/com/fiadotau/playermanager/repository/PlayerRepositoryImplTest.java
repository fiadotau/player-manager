package com.fiadotau.playermanager.repository;

import com.fiadotau.playermanager.model.domain.Player;
import com.fiadotau.playermanager.model.repository.PlayerRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;

import java.util.HashMap;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PlayerRepositoryImplTest {

    private final String PLAYER_ID = "player1Id";
    private PlayerRepositoryImpl playerRepository;
    private HashMap<String, Player> playerMap;

    @BeforeEach
    void setUp() {
        playerMap = new HashMap<>();
        Player player1 = new Player();
        playerMap.put(PLAYER_ID, player1);

        playerRepository = new PlayerRepositoryImpl(playerMap);
    }

    @Test
    void testGetAllPlayers() {
        //Given
        int page = 0;
        int size = 1;

        //When
        Page<Player> playersPage = playerRepository.getAllPlayers(page, size);

        //Then
        assertNotNull(playersPage);
        assertEquals(1, playersPage.getContent().size());
        assertTrue(playersPage.getContent().contains(playerMap.get(PLAYER_ID)));
    }

    @Test
    void testGetPlayerById() {
        //When
        Optional<Player> foundPlayer = playerRepository.getPlayerById(PLAYER_ID);

        //Then
        assertTrue(foundPlayer.isPresent());
        assertEquals(playerMap.get(PLAYER_ID), foundPlayer.get());
    }

    @Test
    void testGetPlayerByIdNotFound() {
        //Given
        String playerId = "nonExistentId";

        //When
        Optional<Player> foundPlayer = playerRepository.getPlayerById(playerId);

        //Then
        assertFalse(foundPlayer.isPresent());
    }
}