package com.systemair.exchangers.domain.exchangers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@ToString
public class Result {
    private double capacity;
    private double tOut;
    private long airDrop;
    private double airVelocity;
    private String fluidDrop;
    private String fluidFlow;
    private String model;
}
