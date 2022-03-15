package com.systemair.exchangers.service;

import com.systemair.exchangers.domain.Process;
import com.systemair.exchangers.domain.exchangers.Exchanger;
import com.systemair.exchangers.domain.exchangers.ExchangerFactory;

import java.util.ArrayList;

import static com.systemair.exchangers.domain.exchangers.Brand.SYSTEMAIR;


public class ExchangersServiceImpl implements ExchangersService {
    private final ExchangerFactory exchangerFactory = new ExchangerFactory();

    @Override
    public Exchanger getExchanger(ArrayList<String> row, Process process) {
        Exchanger exchanger;
        double tIn = Double.parseDouble(row.get(0).replace(",","."));
        int uIn = Integer.parseInt(row.get(1));
        double tOut = Double.parseDouble(row.get(2).replace(",","."));
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
