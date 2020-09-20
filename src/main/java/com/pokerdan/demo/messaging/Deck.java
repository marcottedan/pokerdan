package com.pokerdan.demo.messaging;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Deck {

    List<Card> cards;
}
