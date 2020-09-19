package com.pokerdan.demo.enumeration;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Suits {
    Clubs("♧", "black"),
    Spades("♤", "black"),
    Hearts("♡", "red"),
    Diamonds("♢", "red");


    public final String text;
    public final String color;

    private Suits(String text, String color) {
        this.text = text;
        this.color = color;
    }
}
