package com.systemair.exchangers.service;

import com.systemair.exchangers.staticClasses.SingletonBrowserClass;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import static java.lang.Thread.sleep;
import static org.openqa.selenium.Keys.DELETE;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public abstract class BrowserServiceImpl implements BrowserService {
    private static final Logger LOGGER = Logger.getLogger(BrowserServiceImpl.class.getName());
    public static final SingletonBrowserClass sbc = SingletonBrowserClass.getInstanceOfSingletonBrowserClass();

    @Override
    public void changeValueComboBoxByLabel(String id, String newValue) {
        WebElement selectElement = sbc.getDriver().findElement(By.id(id));
        if (selectElement == null) return;
        if (selectElement.getText().equals(newValue)) return;
        Select selectObject = new Select(selectElement);
        selectObject.selectByValue(newValue);
    }

    @Override
    public void inputTextById(String id, String newValue) {
        WebElement wb = sbc.getWait().until(visibilityOfElementLocated(By.id(id)));
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
        sbc.getWait().until(visibilityOfElementLocated(by));
        LOGGER.info("Кнопка доступна!");
        sbc.getWait().until(elementToBeClickable(by)).click();
        LOGGER.info("Кнопка нажата!");
    }
//    protected boolean isContainsInClass(WebElement webElement, String text) {
//        return webElement.getAttribute("class").contains(text);
//    }
//
//    protected boolean isWarning() {
//        boolean isWarning;
//        By byWarning = By.xpath(".//span[@type='warning']");
//        try {
//            sbc.getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(1500));
//            isWarning = sbc.getDriver().findElements(byWarning).size() > 0;
//        } finally {
//            sbc.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(MAX_LIMIT_TIMEOUT));
//        }
//        LOGGER.info("Warning is " + isWarning);
//        return isWarning;
//    }
//
//    protected boolean isExistElementMoreThenTwo(By by) {
//        boolean isExists;
//        isExists = sbc.getDriver().findElements(by).size() > 2;
//        return isExists;
//    }
//
//    protected void clickWithoutTimeOut(By by) {
//        try {
//            sbc.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
//            sbc.getDriver().findElement(by).click();
//        } finally {
//            sbc.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(MAX_LIMIT_TIMEOUT));
//        }
//    }
//
//    protected WebElement getWebElementByXpath(String xpath) {
//        List<Optional<WebElement>> webElements = sbc.getWait().until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath(xpath), 0)).stream().map(Optional::of).collect(Collectors.toList());
//        return webElements.get(0).orElseThrow(IllegalArgumentException::new);
//    }
//

//
//    protected void clickElementWithScroll(WebElement webElement) {
//        ((JavascriptExecutor) sbc.getDriver()).executeScript("arguments[0].scrollIntoView(true);", webElement);
//        sbc.getWait().until(elementToBeClickable(webElement)).click();
//    }
}
