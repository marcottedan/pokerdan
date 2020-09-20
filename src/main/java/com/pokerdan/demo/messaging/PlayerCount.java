package com.pokerdan.demo.messaging;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PlayerCount {
    private String playerId;
    private Long count;
}
