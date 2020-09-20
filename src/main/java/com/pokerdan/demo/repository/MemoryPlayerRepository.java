package com.pokerdan.demo.repository;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.pokerdan.demo.messaging.Card;

/**
 * Persists in memory, could use an interface + implementation but that leads to more code to maintain.
 * // TODO Use SpringData and use a persistence layer with integration tests & TestContainer
 */
@Repository
public class MemoryPlayerRepository {

    // playerId to List of Cards
    private Map<String, List<Card>> cards = new HashMap<>();

    public List<Card> addCard(Card card, String gameId, String playerId) {
        List<Card> playerHand = cards.get(playerId);
        if (playerHand == null){
            playerHand = new LinkedList<>();
        }
        playerHand.add(card);
        cards.put(playerId, playerHand);

        return playerHand;
    }

    // TODO Handle gameId+playerId constraint to allow multiple games concurrently by players
    public List<Card> getCardsByGameIdAndPlayerId(String gameId, String playerId) {
        List<Card> hand = cards.get(playerId);
        if (hand == null)
        {
            hand = new LinkedList<>();
            cards.put(playerId, hand);
        }

        return hand;
    }
}
