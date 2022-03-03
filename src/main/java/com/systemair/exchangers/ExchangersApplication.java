package com.systemair.exchangers;

import com.systemair.exchangers.domain.Browser;
import com.systemair.exchangers.domain.exchangers.Exchanger;
import com.systemair.exchangers.service.BrowserService;
import com.systemair.exchangers.service.ExchangersService;
import com.systemair.exchangers.service.ExchangersServiceImpl;
import com.systemair.exchangers.service.VeabBrowserService;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;

public class ExchangersApplication {
    private final ExchangersService exchangersService = new ExchangersServiceImpl();
    private final BrowserService browserService = new VeabBrowserService();
    private Browser browser;

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

    public Exchanger run(WebDriver driver, Wait<WebDriver> wait, Exchanger exchanger) {
        if (browser == null) browser = new Browser(driver, wait);
        if (exchanger == null) return null;
        browserService.setBrowser(browser);
        browserService.navigate(exchanger.getURL());
        browserService.fillTechData(exchanger);
        System.out.println(exchanger);
        browserService.calculation(exchanger.getModel());

        exchanger.setResult(browserService.getResult(exchanger.getProcess(), exchanger.getTOut()));
        System.out.println(exchanger.getResult());
        return exchanger;
    }

    public ExchangersService getExchangersService() {
        return exchangersService;
    }
}