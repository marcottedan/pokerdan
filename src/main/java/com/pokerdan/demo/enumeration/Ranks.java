package com.pokerdan.demo.enumeration;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Ranks {
    Ace("1", 1),
    Two("2", 2),
    Three("3", 3),
    Four("4", 4),
    Five("5", 5),
    Six("6", 6),
    Seven("7", 7),
    Eight("8", 8),
    Nine("9", 9),
    Ten("10", 10),
    Jack("🂫", 11),
    Queen("♕", 12),
    King("♔", 13);

    public final String text;
    public final Integer faceValue;

    private Ranks(String text, Integer faceValue) {
        this.text = text;
        this.faceValue = faceValue;
    }
}
