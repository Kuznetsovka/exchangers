package com.systemair.exchangers.domain.exchangers;

import com.systemair.exchangers.domain.TypeMontage;
import com.systemair.exchangers.domain.fluid.Fluid;
import com.systemair.exchangers.domain.fluid.Freon;
import com.systemair.exchangers.domain.fluid.Water;
import lombok.Getter;
import lombok.Setter;

import static com.systemair.exchangers.domain.fluid.Freon.TypeFreon.isFreon;
import static com.systemair.exchangers.domain.fluid.Water.TypeWater.getByDescription;

@Getter
@Setter
public class Exchanger {
    private double tIn;
    private int uIn;
    private int airFlow;
    private Fluid fluid;
    private int tOut;
    private String Model;
    protected TypeMontage typeMontage;
    private Result result;

    public void setFluid(String fluid, int mixture, int tInFluid, int tOutFluid) {
        if (isFreon(fluid))
            this.fluid = new Freon(Freon.TypeFreon.valueOf(fluid), tInFluid);
        else
            this.fluid = new Water(getByDescription(fluid), mixture, tInFluid, tOutFluid);
    }

    public void fillProperties(String typeFluid, Integer mixture, Double tIn, Integer uIn, Integer tOut, Integer airFlow, Integer tInFluid, Integer tOutFluid, Exchanger exchanger) {
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
                ", Model='" + Model + '\'' +
                ", typeMontage=" + typeMontage +
                ", result=" + result +
                '}';
    }
}
