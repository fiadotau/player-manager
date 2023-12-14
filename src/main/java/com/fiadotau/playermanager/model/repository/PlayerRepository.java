package com.fiadotau.playermanager.model.repository;

import com.fiadotau.playermanager.model.domain.Player;
import org.springframework.data.domain.Page;

import java.util.Optional;

/* TODO
 This interface abstracts the underlying data storage mechanism for Player entities. By defining an interface,
 we decouple the service layer from the data access layer, which makes the system more flexible and maintainable.
 For example, if there's a need to switch from the current data storage solution to a relational database or a NoSQL
 database in the future, the change can be implemented seamlessly without affecting the service layer.
 */
public interface PlayerRepository {

    Page<Player> getAllPlayers(int page, int size);

    Optional<Player> getPlayerById(String playerId);
}
