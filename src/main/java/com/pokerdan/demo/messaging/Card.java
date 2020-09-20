package com.pokerdan.demo.messaging;

import com.pokerdan.demo.enumeration.Ranks;
import com.pokerdan.demo.enumeration.Suits;

import lombok.Data;

@Data
public class Card {

    private Ranks rank;
    private Suits suit;

    public Card(Ranks rank, Suits suit) {
        this.rank = rank;
        this.suit = suit;
    }
}
