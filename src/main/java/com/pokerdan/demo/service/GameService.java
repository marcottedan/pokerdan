package com.pokerdan.demo.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pokerdan.demo.messaging.Game;
import com.pokerdan.demo.repository.MemoryGameRepository;

/**
 * Handles creation/deletion of games, no matter what types they are.
 * Do not put any game-specific logic in here.
 */
@Service
public class GameService {

    @Autowired
    private MemoryGameRepository repository;

    public void create(Game game) {
        repository.save(game);
    }

    public boolean delete(String gameId) {
        return repository.delete(gameId);
    }

    public Collection<Game> getAll() {
        return repository.getAll();
    }

    public Optional<Game> get(String gameId) {
        return repository.get(gameId);
    }

    public Optional<Game> join(String gameId, String playerName) {
        // TODO Use server-side session and don't accept the player variable from the Frontend
        // TODO Add Lock at Game-Level to scale and prevent joining the same game concurrently
        // locks.tryAcquireAndExecute(gameId, () -> { /*...*/ });

        Optional<Game> gameOpt = repository.get(gameId);
        if (!gameOpt.isPresent()) {
            return gameOpt;
        }
        Game game = gameOpt.get();
        if (game.getPlayerIds().contains(playerName)) {
            return gameOpt;
        }

        game.getPlayerIds().add(playerName);
        repository.save(game);

        return gameOpt;
    }

    public Optional<Game> quit(String gameId, String playerName) {
        // TODO Use server-side session and don't accept the player variable from the Frontend

        Optional<Game> gameOpt = repository.get(gameId);
        if (!gameOpt.isPresent()) {
            return gameOpt;
        }
        Game game = gameOpt.get();
        if (!game.getPlayerIds().contains(playerName)) {
            return gameOpt;
        }

        game.getPlayerIds().remove(playerName);
        repository.save(game);

        return gameOpt;
    }

}
