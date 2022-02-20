package com.systemair.exchangers.service;

import com.systemair.exchangers.domain.exchangers.Exchanger;
import com.systemair.exchangers.domain.models.*;

import static com.systemair.exchangers.domain.Process.HEAT;
import static com.systemair.exchangers.domain.TypeMontage.ROUND;
import static com.systemair.exchangers.domain.fluid.Freon.TypeFreon.isFreon;

public class ExchangersServiceImpl implements ExchangersService {
    /**
     * 0 - Тип монтажа
     * 1 - Тип теплообменника
     * 2 - Тип жидкости
     * 3 - процент смеси
     * 4 - Т входа
     * 5 - U входа
     * 6 - Т выхода
     * 7 - Расход
     * 8 - Т входа жидкости
     * 9 - Т выхода жидкости
     */
    @Override
    public Exchanger createExchanger(String[] args) {
        Exchanger exchanger;
        String typeMontage = args[0];
        String process = args[1];
        String typeFluid = args[2];
        int mixture = Integer.parseInt(args[3]);
        int tIn = Integer.parseInt(args[4]);
        int uIn = Integer.parseInt(args[5]);
        int tOut = Integer.parseInt(args[6]);
        int airFlow = Integer.parseInt(args[7]);
        int tInFluid = Integer.parseInt(args[8]);
        int tOutFluid = Integer.parseInt(args[9]);
        String model = (args.length == 11) ? args[10] : "";
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
        exchanger.fillProperties(typeFluid, mixture, tIn, uIn, tOut, airFlow, tInFluid, tOutFluid, exchanger);
        //exchanger.setModel(model);
        return exchanger;
    }

}
