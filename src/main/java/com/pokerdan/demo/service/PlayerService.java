package com.pokerdan.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pokerdan.demo.messaging.Card;
import com.pokerdan.demo.repository.MemoryPlayerRepository;

@Service
public class PlayerService {

    @Autowired
    private MemoryPlayerRepository repository;

    public List<Card> getPlayerHand(String gameId, String playerId) {
        return repository.getCardsByGameIdAndPlayerId(gameId, playerId);
    }

    public void addCard(Card card, String gameId, String playerId) {
        repository.addCard(card, gameId, playerId);
    }
}
