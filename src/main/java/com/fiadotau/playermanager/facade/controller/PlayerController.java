package com.fiadotau.playermanager.facade.controller;

import com.fiadotau.playermanager.model.domain.Player;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import com.fiadotau.playermanager.model.service.PlayerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/players")
@RequiredArgsConstructor
public class PlayerController {

    private final PlayerService playerService;

    @GetMapping
    public ResponseEntity<Page<Player>> getAllPlayers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<Player> players = playerService.getAllPlayers(page, size);
        /* TODO
           In a situation of more time, it would be beneficial to use Data Transfer Objects here and corresponding Mappers.
           DTOs will help in separating the external representation of data from the internal database entities.
         */
        return ResponseEntity.ok(players);
    }

    @GetMapping("/{playerID}")
    public ResponseEntity<Player> getPlayerById(@PathVariable String playerID) {

        return playerService.getPlayerById(playerID)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
