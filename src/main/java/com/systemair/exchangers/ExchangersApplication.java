package com.systemair.exchangers;

import com.systemair.exchangers.domain.exchangers.Exchanger;
import com.systemair.exchangers.domain.exchangers.ExchangerFactory;
import com.systemair.exchangers.domain.exchangers.Heater;
import com.systemair.exchangers.service.*;
import lombok.Getter;

public class ExchangersApplication {
    private final ExchangersService exchangersService = new ExchangersServiceImpl();
    private final BrowserService browserService = new VeabBrowserService();

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
     * 10 - Модель
     */

    public Exchanger run(Exchanger exchanger) {
        browserService.navigate(exchanger.getURL());
        browserService.fillTechData(exchanger);
        System.out.println(exchanger);
        // В конце
        //browserService.selectModel("PGV 500x400-4-2,5");
        exchanger.setResult(browserService.getResult(exchanger.getProcess(), exchanger.getTOut()));
        System.out.println(exchanger.getResult());
        return exchanger;
    }

    public void stop(){
        browserService.stop();
    }

    public ExchangersService getExchangersService() {
        return exchangersService;
    }
}