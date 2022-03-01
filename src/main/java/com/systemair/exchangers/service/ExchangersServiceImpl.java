package com.systemair.exchangers.service;

import com.systemair.exchangers.domain.Process;
import com.systemair.exchangers.domain.exchangers.Exchanger;
import com.systemair.exchangers.domain.exchangers.ExchangerFactory;

import java.util.ArrayList;


public class ExchangersServiceImpl implements ExchangersService {
    private final ExchangerFactory exchangerFactory = new ExchangerFactory();

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
    public Exchanger getExchanger(String[] args) {
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
        exchanger = exchangerFactory.createExchanger(typeMontage, process, typeFluid);
        exchanger.fillProperties(typeFluid, mixture, tIn, uIn, tOut, airFlow, tInFluid, tOutFluid, exchanger);
        if (!model.isEmpty()) exchanger.setModel(model);
        return exchanger;
    }

    @Override
    public Exchanger getExchanger(ArrayList<String> row, Process process) {
        Exchanger exchanger;

        int tIn = Integer.parseInt(row.get(0));
        int uIn = Integer.parseInt(row.get(1));
        int tOut = Integer.parseInt(row.get(2));
        String typeFluid = row.get(3);
        int mixture = !row.get(4).isEmpty() ? Integer.parseInt(row.get(4)) : 0;
        int tInFluid = Integer.parseInt(row.get(5));
        int tOutFluid = Integer.parseInt(row.get(6));
        String model = row.get(7);
        String typeMontage = row.get(8);
        int airFlow = Integer.parseInt(row.get(9));
        exchanger = exchangerFactory.createExchanger(typeMontage, process.getTxt(), typeFluid);
        exchanger.fillProperties(typeFluid, mixture, tIn, uIn, tOut, airFlow, tInFluid, tOutFluid, exchanger);
        if (!model.isEmpty()) exchanger.setModel(model);
        return exchanger;
    }
}
