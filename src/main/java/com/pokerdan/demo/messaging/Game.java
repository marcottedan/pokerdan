package com.pokerdan.demo.messaging;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import lombok.Data;

@Data
public class Game {

    private String id;

    private List<String> playerIds;

    public Game() {
        this.id = UUID.randomUUID().toString().replace("-", "");
        this.playerIds = new ArrayList<>();
    }
}
