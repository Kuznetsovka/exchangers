package com.systemair.exchangers.domain.exchangers;

import com.systemair.exchangers.domain.exchangers.models.*;

import static com.systemair.exchangers.domain.Process.HEAT;
import static com.systemair.exchangers.domain.TypeMontage.ROUND;
import static com.systemair.exchangers.domain.fluid.Freon.TypeFreon.isFreon;

public class ExchangerFactory {
    public Exchanger createExchanger(String typeMontage, String process, String typeFluid) {
        Exchanger exchanger;
        if (typeMontage.equals(ROUND.getTxt())) {
            if (process.equals(HEAT.getTxt())) {
                exchanger = new RoundHeater();
            } else {
                exchanger = new RoundCooler();
            }
        } else {
            if (process.equals(HEAT.getTxt())) {
                exchanger = new RectangleHeater();
            } else if (isFreon(typeFluid)) {
                exchanger = new FreonCooler();
            } else {
                exchanger = new RectangleCooler();
            }
        }
        return exchanger;
    }
}
