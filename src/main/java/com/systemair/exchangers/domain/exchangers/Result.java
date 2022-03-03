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
    private Value capacity;
    private Value tOut;
    private Value airDrop;
    private Value airVelocity;
    private Value fluidDrop;
    private Value fluidFlow;
    private String model;
}
