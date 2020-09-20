package com.pokerdan.demo.util;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Component;

import com.pokerdan.demo.messaging.Card;
import com.pokerdan.demo.messaging.Deck;

/**
 * Business logic to manipulate a Deck (shuffle, deal card, ect)
 */
@Component
public class DeckShuffler {

    public Optional<Card> getCard(Deck deck) {

        if (deck.getCards().size() == 0) {
            return Optional.empty();
        }

        Card card = deck.getCards().remove(0);
        return Optional.of(card);
    }

    public Deck shuffleDeck(Deck deck) {

        if (deck.getCards().size() == 0) {
            return deck;
        }

        List<Card> cards = fisherYatesShuffle(deck.getCards());
        deck.setCards(cards);
        return deck;
    }

    // http://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle#The_modern_algorithm: O(n) sorting algorithm
    private List<Card> fisherYatesShuffle(List<Card> cards) {

        // Creating a object for Random class
        Random r = new Random();
        int n = cards.size();

        // Start from the last element and swap one by one. We don't
        // need to run for the first element that's why i > 0
        for (int i = n - 1; i > 0; i--) {

            // Pick a random index from 0 to i
            int j = r.nextInt(i + 1);

            // Swap cards[i] with the element at random index
            Card temp = cards.get(i);
            cards.set(i, cards.get(j));
            cards.set(j, temp);
        }

        return cards;
    }
}
