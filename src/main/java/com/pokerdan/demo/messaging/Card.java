package com.pokerdan.demo.messaging;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Card {

    private String suit;
    private int faceValue;
}
