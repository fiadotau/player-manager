package com.fiadotau.playermanager.service;

import com.fiadotau.playermanager.model.domain.Player;
import com.fiadotau.playermanager.model.repository.PlayerRepositoryImpl;
import com.fiadotau.playermanager.model.service.PlayerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PlayerServiceTest {

    @Mock
    private PlayerRepositoryImpl playerRepository;

    @InjectMocks
    private PlayerService playerService;

    @Test
    void testGetAllPlayers() {
        // Given
        Player player = new Player();
        player.setPlayerId("playerId");

        Page<Player> expectedPage = new PageImpl<>(List.of(player), PageRequest.of(0, 1), 1);
        when(playerRepository.getAllPlayers(0, 1)).thenReturn(expectedPage);

        // When
        Page<Player> resultPage = playerService.getAllPlayers(0, 1);

        // Then
        assertEquals(1, resultPage.getNumberOfElements());
        assertEquals(player, resultPage.getContent().get(0));
    }

    @Test
    void testGetPlayerById() {
        // Given
        String playerId = "playerId";
        Player expectedPlayer = new Player();
        when(playerRepository.getPlayerById(playerId)).thenReturn(Optional.of(expectedPlayer));

        // When
        Optional<Player> result = playerService.getPlayerById(playerId);

        // Then
        assertTrue(result.isPresent());
        assertEquals(expectedPlayer, result.get());
    }

    @Test
    void testGetPlayerByIdNotFound() {
        // Given
        String playerId = "nonExistentId";
        when(playerRepository.getPlayerById(playerId)).thenReturn(Optional.empty());

        // When
        Optional<Player> result = playerService.getPlayerById(playerId);

        // Then
        assertFalse(result.isPresent());
    }
}