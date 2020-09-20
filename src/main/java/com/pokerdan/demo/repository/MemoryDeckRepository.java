package com.pokerdan.demo.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.pokerdan.demo.messaging.Deck;

/**
 * Persists in memory, could use an interface + implementation but that leads to more code to maintain.
 * // TODO Use SpringData and use a persistence layer with integration tests & TestContainer
 */
@Repository
public class MemoryDeckRepository {

    private Map<String, Deck> decks = new HashMap<>();

    public void save(Deck deck, String gameId) {
        decks.put(gameId, deck);
    }

    public Optional<Deck> get(String gameId) {
        return Optional.ofNullable(decks.get(gameId));
    }

}
