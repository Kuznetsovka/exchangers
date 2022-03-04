package com.systemair.exchangers.service;

import com.systemair.exchangers.domain.Process;
import com.systemair.exchangers.domain.exchangers.Exchanger;
import com.systemair.exchangers.domain.exchangers.ExchangerFactory;

import java.util.ArrayList;

import static com.systemair.exchangers.domain.exchangers.Brand.SYSTEMAIR;
import static com.systemair.exchangers.domain.exchangers.Brand.VEAB;


public class ExchangersServiceImpl implements ExchangersService {
    private final ExchangerFactory exchangerFactory = new ExchangerFactory();

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
        exchanger.fillProperties(typeFluid, mixture, tIn, uIn, tOut, airFlow, tInFluid, tOutFluid);
        if (!model.isEmpty()) exchanger.setModel(model,SYSTEMAIR);
        return exchanger;
    }
}
