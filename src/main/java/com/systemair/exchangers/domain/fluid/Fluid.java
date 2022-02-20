package com.systemair.exchangers.domain.fluid;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Fluid {
    protected int tInFluid;
    protected Integer mixture;
    protected int tOutFluid;
    private TypeFluid type;
}
