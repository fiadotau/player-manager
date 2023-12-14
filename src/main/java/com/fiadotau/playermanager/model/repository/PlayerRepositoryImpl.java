package com.fiadotau.playermanager.model.repository;

import com.fiadotau.playermanager.model.domain.Player;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class PlayerRepositoryImpl implements PlayerRepository {

    private final Map<String, Player> playerMap;

    public Page<Player> getAllPlayers(int page, int size) {
        List<Player> players = new ArrayList<>(playerMap.values());
        Pageable pageable = PageRequest.of(page, size);

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), players.size());
        List<Player> pageContent = players.subList(start, end);

        return new PageImpl<>(pageContent, pageable, players.size());
    }

    public Optional<Player> getPlayerById(String playerId) {
        return Optional.ofNullable(playerMap.get(playerId));
    }
}