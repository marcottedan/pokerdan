package com.pokerdan.demo.messaging;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeckRankCount {

    public long ace;
    public long two;
    public long three;
    public long four;
    public long five;
    public long six;
    public long seven;
    public long eight;
    public long nine;
    public long ten;
    public long jack;
    public long queen;
    public long king;

}
