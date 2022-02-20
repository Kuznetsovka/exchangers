package com.systemair.exchangers.staticClasses;


import com.systemair.exchangers.staticClasses.browsers.ChromeBrowser;
import com.systemair.exchangers.staticClasses.browsers.EdgeBrowser;
import org.apache.log4j.Logger;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;

import static com.systemair.exchangers.staticClasses.UtilClass.BROWSER;
import static java.lang.System.exit;

public class SingletonBrowserClass {

    private static SingletonBrowserClass instanceOfSingletonBrowserClass = null;
    private static final Logger LOGGER = Logger.getLogger(SingletonBrowserClass.class.getName());
    private static Wait wait;
    private static WebDriver driver;

    private SingletonBrowserClass() {
        try {
            if (BROWSER.isEmpty() || BROWSER.equals("CHROME")) {
                ChromeBrowser browser = new ChromeBrowser();
                wait = browser.getWait();
                driver = browser.getDriver();
            } else if (BROWSER.equals("EDGE")) {
                EdgeBrowser browser = new EdgeBrowser();
                wait = browser.getWait();
                driver = browser.getDriver();
            }
            //driver.navigate().to(HOME_URL);
        } catch (SessionNotCreatedException e) {
            LOGGER.warn("Обновите драйвер браузера!" + "\n" + e.getRawMessage());
            if (driver != null) driver.quit();
            exit(0);
        }
    }

    public synchronized static SingletonBrowserClass getInstanceOfSingletonBrowserClass() {
        if (instanceOfSingletonBrowserClass == null || driver == null) {
            instanceOfSingletonBrowserClass = new SingletonBrowserClass();
        }
        return instanceOfSingletonBrowserClass;
    }

    public synchronized WebDriver getDriver() {
        return driver;
    }

    public Wait<WebDriver> getWait() {
        return wait;
    }
}