package com.systemair.exchangers.domain.exchangers;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Result {
    private double capacity;
    private double tOut;
    private double airDrop;
    private double airVelocity;
    private double fluidDrop;
    private double fluidFlow;
    private String model;

}
