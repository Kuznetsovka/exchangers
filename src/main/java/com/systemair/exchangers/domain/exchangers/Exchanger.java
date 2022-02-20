package com.systemair.exchangers.domain.exchangers;

import com.systemair.exchangers.domain.TypeMontage;
import com.systemair.exchangers.domain.fluid.Fluid;

public abstract class Exchanger {
    private double tIn;
    private int uIn;
    private int airFlow;
    private Fluid fluid;
    private int tOut;
    private String Model;
    private TypeMontage typeMontage;
}
