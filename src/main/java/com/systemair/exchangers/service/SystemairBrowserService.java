package com.systemair.exchangers.service;

import com.systemair.exchangers.staticClasses.SingletonBrowserClass;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class SystemairBrowserService extends BrowserServiceImpl {
//    protected String positiveLimit = "100";
//    protected String negativeLimit = "0";
//    private boolean isClear;
//    boolean flagWarning;
//    boolean isSorting;
//    boolean isHidingDiagram;
//    private boolean isGrouping;
//    private static final Logger LOGGER = Logger.getLogger(SystemairBrowserService.class.getName());
//    private boolean isChangeMeasureValueTable;
//
//    public SystemairBrowserService() {
//        super();
//    }
//
//    @Override
//    public void prepareStartPageBeforeCalculation() {
//        try {
//            // Внесение данных Отрицательный допуск
//            inputTextByLabel("Отрицательный допуск", negativeLimit);
//            LOGGER.info("Заполнен отрицательный допуск");
//            // Внесение данных Положительный допуск
//            inputTextByLabel("Положительный допуск", positiveLimit);
//            LOGGER.info("Заполнен положительный допуск");
//            // Проверка и изменение значения Макс. температура воздуха на 40
//            inputTextByLabel("Макс. температура воздуха", "40");
//            LOGGER.info("Заполнена макс. температура воздуха");
//        } catch (InterruptedException e) {
//            LOGGER.error(e.getMessage());
//            e.printStackTrace();
//        }
//
//    }
//
//    @Override
//    public void inputTextByLabel(String findTextLabel, String newValue) throws InterruptedException {
//        // "fgkAsr" - без ошибки "lnjRPV" - c ошибкой
//        String checkXPath = ".//span[text() = '" + findTextLabel + "']";
//        String xpath = checkXPath + "/following::input[1]";
//        By by = By.xpath(xpath);
//        WebElement wb = sbc.getWait().until(visibilityOfElementLocated(by));
//        if (wb.getAttribute("value").equals(newValue)) return;
//        LOGGER.info("Заполнено текстовое поле, значение: " + newValue);
//        do {
//            wb.sendKeys(Keys.CONTROL + "a");
//            wb.sendKeys(Keys.DELETE);
//        } while (!wb.getAttribute("value").equals(""));
//        sbc.getWait().until(attributeToBe(By.xpath(checkXPath), "class", "sc-jwKygS lnjRPV"));
//        do {
//            wb.sendKeys(newValue);
//        } while (!wb.getAttribute("value").equals(newValue));
//        sbc.getWait().until(attributeToBe(By.xpath(checkXPath), "class", "sc-jwKygS fgkAsr"));
//    }
//
//    private int getLastRows() {
//        return sbc.getWait().until(visibilityOfAllElementsLocatedBy(By.xpath(".//table[@class='sc-Rmtcm djcDFD']/tbody/tr[@class='sc-bRBYWo hmjjYh']"))).size();
//    }
//
//    private void clickButtonMoreFans(By moreFansButtonBy) {
//        WebElement btnMoreUnit;
//        btnMoreUnit = sbc.getWait().until(visibilityOfAllElementsLocatedBy(moreFansButtonBy)).get(2);
//        sbc.getWait().until(elementToBeClickable(btnMoreUnit)).click();
//        LOGGER.info("Нажата кнопка больше вентиляторов.");
//    }
//
//    @Override
//    public void fillFlowAndDrop(String airFlow, String airDrop) {
//        try {
//            inputTextByLabel("Расход воздуха", airFlow);
//            inputTextByLabel("Внешнее давление", airDrop);
//            clickElementIfExistsByXpath("(.//button[@class='sc-bxivhb SWiNZ'])[2]");
//        } catch (InterruptedException | ElementClickInterceptedException e) {
//            LOGGER.error(e.getMessage());
//            e.printStackTrace();
//        }
//        if (isWarning())
//            flagWarning = true;
//    }
//
//    @Override
//    public void changeMeasureValueOnTableByIndex(String newValue, int index) {
//        String xpath = ".//th[@class='sc-hzDkRC kmzkGx'][" + index + "]/div[2]/div[1]";
//        String checkingXpath = xpath + "/span[1]";
//        WebElement checkingWb = sbc.getWait().until(visibilityOfElementLocated(By.xpath(checkingXpath)));
//        if (checkingWb.getText().equals(newValue)) return;
//        sbc.getWait().until(elementToBeClickable(By.xpath(xpath))).click();
//        List<WebElement> list = sbc.getWait().until(numberOfElementsToBeMoreThan(By.xpath(".//div[@class='sc-EHOje gdmUuL']/following::div[2]/div"), 0));
//        WebElement changingElement = list.stream().filter(webElement -> webElement.getText().trim().equals(newValue)).findAny().orElse(null);
//        if (changingElement == null)
//            showAlert(LOGGER, "Запрос " + xpath + " не дал результата! Значение " + newValue + " не было найдено в списке!", Alert.AlertType.WARNING);
//        sbc.getWait().until(elementToBeClickable(changingElement)).click();
//        LOGGER.info("Заменили значение изменения на " + newValue);
//        isChangeMeasureValueTable = true;
//    }
//
//    @Override
//    public void onCheckbox(boolean onAction, WebElement webElement) {
//        /*
//        "kClLXW" - выкл.
//        "eITjnS" - вкл.
//         */
//        if (onAction) {
//            if (isContainsInClass(webElement, "kClLXW")) webElement.click();
//        } else {
//            if (isContainsInClass(webElement, "eITjnS")) webElement.click();
//        }
//    }
//
//    @Override
//    public void onCheckboxDiagram(WebElement webElement) {
//        /*
//        "ineogT" - выкл.
//        "hBEpsK" - вкл.
//         */
//        if (isContainsInClass(webElement, "hBEpsK")) webElement.click();
//    }
//
//    @Override
//    public void initializeBrowser() {
//        clickElementIfExistsByXpath(".//*[@id='CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll']");
//        // Нажатие на вкладку  Подбор
//        clickElementIfExistsByXpath(".//button[@data-id='2']");
//        // Открытие вкладки Дополнительные параметры поиска
//        clickElementIfExistsByXpath(".//div[text() = 'Дополнительные параметры поиска']/i[1]", "class", "fa fa-chevron-down");
//    }
//
//    @Override
//    public void setNegativeLimit(String negativeLimit) {
//        this.negativeLimit = negativeLimit;
//    }
//
//    @Override
//    public void setPositiveLimit(String positiveLimit) {
//        this.positiveLimit = positiveLimit;
//    }
//
//    @Override
//    public SingletonBrowserClass getSbc() {
//        return sbc;
//    }
}
