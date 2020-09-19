package com.pokerdan.demo.repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.pokerdan.demo.messaging.Game;

/**
 * Persists in memory, could use an interface + implementation but that leads to more code to maintain.
 * // TODO Use SpringData and use a persistence layer with integration tests & TestContainer
 */
@Repository
public class MemoryGameRepository {

    private Map<String, Game> games = new HashMap<>();

    public void save(Game game) {
        games.put(game.getId(), game);
    }

    public Optional<Game> get(String gameId) {
        return Optional.ofNullable(games.get(gameId));
    }

    public Collection<Game> getAll(){
        return games.values();
    }

    public boolean delete(String gameId) {
        return games.remove(gameId) == null ? false : true;
    }
}
