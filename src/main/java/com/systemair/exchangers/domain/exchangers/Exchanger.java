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
    protected double tIn;
    protected int uIn;
    protected int airFlow;
    protected Fluid fluid;
    protected double tOut;
    public String URL;
    protected TypeMontage typeMontage;
    protected Result result;
    protected String model;
    protected Process process;

    public void setFluid(String fluid, int mixture, int tInFluid, int tOutFluid) {
        if (isFreon(fluid))
            this.fluid = new Freon(Freon.TypeFreon.valueOf(fluid), tInFluid);
        else
            this.fluid = new Water(getByDescription(fluid), mixture, tInFluid, tOutFluid);
    }

    public void fillProperties(String typeFluid, Integer mixture, double tIn, Integer uIn, double tOut, Integer airFlow, Integer tInFluid, Integer tOutFluid) {
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

    public abstract void setModel(String model, Brand brand);

    public abstract String getModel();

    public abstract Process getProcess();

    public abstract String getModelSystemair();
}
