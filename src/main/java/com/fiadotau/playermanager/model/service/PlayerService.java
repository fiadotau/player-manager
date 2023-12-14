package com.fiadotau.playermanager.model.service;

import com.fiadotau.playermanager.model.domain.Player;
import com.fiadotau.playermanager.model.repository.PlayerRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlayerService {

    private final PlayerRepositoryImpl playerRepository;

    public Page<Player> getAllPlayers(int page, int size) {
        return playerRepository.getAllPlayers(page, size);
    }

    /* TODO
     In the case more than 3 hours it would nice to use Spring's @Cacheable annotation here to improve performance
     for frequent requests by reducing the need to repeatedly query the data source.
     */
    public Optional<Player> getPlayerById(String playerId) {
        return playerRepository.getPlayerById(playerId);
    }
}
