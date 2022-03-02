package com.systemair.exchangers.domain.exchangers;

import com.systemair.exchangers.domain.Process;
import com.systemair.exchangers.domain.TypeMontage;
import com.systemair.exchangers.domain.fluid.Fluid;
import com.systemair.exchangers.domain.fluid.Freon;
import com.systemair.exchangers.domain.fluid.Water;
import com.systemair.exchangers.myInterface.Modifiable;
import lombok.Getter;
import lombok.Setter;

import static com.systemair.exchangers.domain.fluid.Freon.TypeFreon.isFreon;
import static com.systemair.exchangers.domain.fluid.Water.TypeWater.getByDescription;


@Getter
@Setter
public abstract class Exchanger implements Modifiable {
    protected int tIn;
    protected int uIn;
    protected int airFlow;
    protected Fluid fluid;
    protected int tOut;
    public String URL;
    protected TypeMontage typeMontage;
    protected Result result;
    protected String model;


    public void setFluid(String fluid, int mixture, int tInFluid, int tOutFluid) {
        if (isFreon(fluid))
            this.fluid = new Freon(Freon.TypeFreon.valueOf(fluid), tInFluid);
        else
            this.fluid = new Water(getByDescription(fluid), mixture, tInFluid, tOutFluid);
    }

    public void fillProperties(String typeFluid, Integer mixture, int tIn, Integer uIn, Integer tOut, Integer airFlow, Integer tInFluid, Integer tOutFluid, Exchanger exchanger) {
        this.setTIn(tIn);
        this.setUIn(uIn);
        this.setTOut(tOut);
        this.setAirFlow(airFlow);
        this.setFluid(typeFluid, mixture, tInFluid, tOutFluid);
    }

    @Override
    public String toString() {
        return "Exchanger{" +
                "tIn=" + tIn +
                ", uIn=" + uIn +
                ", airFlow=" + airFlow +
                ", fluid=" + fluid +
                ", tOut=" + tOut +
                ", typeMontage=" + typeMontage +
                ", result=" + result +
                '}';
    }

    public Fluid getFluid() {
        return fluid;
    }

    public abstract void setModel(String model);

    public abstract String getModel();

    public abstract Process getProcess();

    public abstract String getModelByVeabModel(String model);
}
