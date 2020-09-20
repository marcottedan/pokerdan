package com.pokerdan.demo.messaging;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlayerHandCount {

    List<PlayerCount> playerValues;
}
