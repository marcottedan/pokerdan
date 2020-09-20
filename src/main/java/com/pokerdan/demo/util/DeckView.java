package com.pokerdan.demo.util;

import java.util.List;

import org.springframework.stereotype.Component;

import com.pokerdan.demo.enumeration.Ranks;
import com.pokerdan.demo.enumeration.Suits;
import com.pokerdan.demo.messaging.Card;
import com.pokerdan.demo.messaging.Deck;

/**
 * Business logic used to calculate value of cards in a deck
 */
@Component
public class DeckView {

    public long countBySuit(Deck deck, Suits suit) {
        long count = deck.getCards().stream().filter(card -> card.getSuit() == suit).count();
        return count;
    }

    public long countByRank(Deck deck, Ranks rank) {
        long count = deck.getCards().stream().filter(card -> card.getRank() == rank).count();
        return count;
    }

    public long sumFaceValue(List<Card> cards) {
        return cards.stream().mapToLong(card -> card.getRank().faceValue).sum();
    }
}
