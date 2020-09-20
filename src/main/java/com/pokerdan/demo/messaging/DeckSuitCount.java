package com.pokerdan.demo.messaging;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeckSuitCount {

    public long clubs;
    public long spades;
    public long hearts;
    public long diamonds;

}
