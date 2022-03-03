package com.systemair.exchangers.service;

import com.systemair.exchangers.domain.Browser;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


import static java.lang.Thread.sleep;
import static org.openqa.selenium.Keys.DELETE;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public abstract class BrowserServiceImpl implements BrowserService {
    protected Browser browser;
    private static final Logger LOGGER = Logger.getLogger(BrowserServiceImpl.class.getName());

    @Override
    public void changeValueComboBoxByLabel(String id, String newValue) {
        WebElement selectElement = browser.getDriver().findElement(By.id(id));
        if (selectElement == null) return;
        if (selectElement.getText().equals(newValue)) return;
        Select selectObject = new Select(selectElement);
        selectObject.selectByValue(newValue);
    }

    @Override
    public void inputTextById(String id, String newValue) {
        WebElement wb = browser.getWait().until(visibilityOfElementLocated(By.id(id)));
        if (wb.getAttribute("data-value-si").equals(newValue)) return;
        LOGGER.info("Заполнено текстовое поле, значение: " + newValue);
        try {
            wb.click();
            sleep(20);
            wb.sendKeys(DELETE);
            sleep(20);
            wb.sendKeys(newValue);
            sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected void clickElementIfExistsByXpath(String xpath) throws ElementClickInterceptedException {
        By by = By.xpath(xpath);
        browser.getWait().until(visibilityOfElementLocated(by));
        LOGGER.info("Кнопка доступна!");
        try {
            WebElement myelement = browser.getDriver().findElement(by);
            JavascriptExecutor jse2 = (JavascriptExecutor) browser.getDriver();
            jse2.executeScript("arguments[0].click()", myelement);
        } catch (ElementClickInterceptedException e) {
            e.printStackTrace();
        }
        LOGGER.info("Кнопка нажата!");
    }
}
