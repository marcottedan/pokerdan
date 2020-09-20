package com.pokerdan.demo.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.pokerdan.demo.enumeration.Ranks;
import com.pokerdan.demo.enumeration.Suits;
import com.pokerdan.demo.messaging.Card;
import com.pokerdan.demo.messaging.Deck;

@Component
public class DeckFactory {

    public Deck newDeck() {
        List<Card> cards = DeckFactory.newCardTemplate();
        return Deck.builder().cards(cards).build();
    }

    // JIT will optimize
    private static final List<Card> newCardTemplate(){
        final List<Card> cards = new ArrayList<>(52);
        cards.add(new Card(Ranks.Ace, Suits.Clubs));
        cards.add(new Card(Ranks.Ace, Suits.Diamonds));
        cards.add(new Card(Ranks.Ace, Suits.Hearts));
        cards.add(new Card(Ranks.Ace, Suits.Spades));
        cards.add(new Card(Ranks.Two, Suits.Clubs));
        cards.add(new Card(Ranks.Two, Suits.Diamonds));
        cards.add(new Card(Ranks.Two, Suits.Hearts));
        cards.add(new Card(Ranks.Two, Suits.Spades));
        cards.add(new Card(Ranks.Three, Suits.Clubs));
        cards.add(new Card(Ranks.Three, Suits.Diamonds));
        cards.add(new Card(Ranks.Three, Suits.Hearts));
        cards.add(new Card(Ranks.Three, Suits.Spades));
        cards.add(new Card(Ranks.Four, Suits.Clubs));
        cards.add(new Card(Ranks.Four, Suits.Diamonds));
        cards.add(new Card(Ranks.Four, Suits.Hearts));
        cards.add(new Card(Ranks.Four, Suits.Spades));
        cards.add(new Card(Ranks.Five, Suits.Clubs));
        cards.add(new Card(Ranks.Five, Suits.Diamonds));
        cards.add(new Card(Ranks.Five, Suits.Hearts));
        cards.add(new Card(Ranks.Five, Suits.Spades));
        cards.add(new Card(Ranks.Six, Suits.Clubs));
        cards.add(new Card(Ranks.Six, Suits.Diamonds));
        cards.add(new Card(Ranks.Six, Suits.Hearts));
        cards.add(new Card(Ranks.Six, Suits.Spades));
        cards.add(new Card(Ranks.Seven, Suits.Clubs));
        cards.add(new Card(Ranks.Seven, Suits.Diamonds));
        cards.add(new Card(Ranks.Seven, Suits.Hearts));
        cards.add(new Card(Ranks.Seven, Suits.Spades));
        cards.add(new Card(Ranks.Eight, Suits.Clubs));
        cards.add(new Card(Ranks.Eight, Suits.Diamonds));
        cards.add(new Card(Ranks.Eight, Suits.Hearts));
        cards.add(new Card(Ranks.Eight, Suits.Spades));
        cards.add(new Card(Ranks.Nine, Suits.Clubs));
        cards.add(new Card(Ranks.Nine, Suits.Diamonds));
        cards.add(new Card(Ranks.Nine, Suits.Hearts));
        cards.add(new Card(Ranks.Nine, Suits.Spades));
        cards.add(new Card(Ranks.Ten, Suits.Clubs));
        cards.add(new Card(Ranks.Ten, Suits.Diamonds));
        cards.add(new Card(Ranks.Ten, Suits.Hearts));
        cards.add(new Card(Ranks.Ten, Suits.Spades));
        cards.add(new Card(Ranks.Jack, Suits.Clubs));
        cards.add(new Card(Ranks.Jack, Suits.Diamonds));
        cards.add(new Card(Ranks.Jack, Suits.Hearts));
        cards.add(new Card(Ranks.Jack, Suits.Spades));
        cards.add(new Card(Ranks.Queen, Suits.Clubs));
        cards.add(new Card(Ranks.Queen, Suits.Diamonds));
        cards.add(new Card(Ranks.Queen, Suits.Hearts));
        cards.add(new Card(Ranks.Queen, Suits.Spades));
        cards.add(new Card(Ranks.King, Suits.Clubs));
        cards.add(new Card(Ranks.King, Suits.Diamonds));
        cards.add(new Card(Ranks.King, Suits.Hearts));
        cards.add(new Card(Ranks.King, Suits.Spades));
        return cards;
    }
}
