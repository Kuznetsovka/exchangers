package com.systemair.exchangers.service;

import com.systemair.exchangers.domain.exchangers.Exchanger;
import com.systemair.exchangers.domain.exchangers.Result;
import com.systemair.exchangers.domain.fluid.Water;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.systemair.exchangers.domain.fluid.Freon.TypeFreon.isFreon;
import static java.lang.System.exit;

public class VeabBrowserService extends BrowserServiceImpl {

    @Override
    public void navigate(String url) {
        sbc.getDriver().navigate().to(url);
    }

    @Override
    public void selectModel(String model) {
        changeValueComboBoxByLabel("selSingleSelection", model);
    }

    @Override
    public void stop() {
        //LOGGER.info("Закрытие сессии!");
        sbc.getDriver().quit();
        exit(0);
    }

    @Override
    public void fillTechData(Exchanger exchanger) {
        inputTextById("txtInletDryBulbTemperature", String.valueOf(exchanger.getTIn()));
        inputTextById("txtRelativeHumidity", String.valueOf(exchanger.getUIn()));
        inputTextById("txtVolumetricFlowAir", String.valueOf(exchanger.getAirFlow()));
        changeValueComboBoxByLabel("selFluid", String.valueOf(exchanger.getFluid().getType().getValue()));
        if (!isFreon(exchanger.getFluid().getType().getTxt())) {
            inputTextById("txtInletTemperature", String.valueOf(exchanger.getFluid().getTInFluid()));
            inputTextById("txtOutletTemparature", String.valueOf(exchanger.getFluid().getTOutFluid()));
            if (!exchanger.getFluid().getType().getTxt().equals(Water.TypeWater.WATER.getTxt())) {
                inputTextById("txtMixtureRate", String.valueOf(exchanger.getFluid().getMixture()));
            }
        }
        inputTextById("txtAirOutletTemperature", String.valueOf(exchanger.getTOut()));
        clickElementIfExistsByXpath("//*[@id='Data']/div[13]/button");
        waitResult();
    }

    @Override
    public Result getResult(int tOut) {
        int rows = sbc.getDriver().findElements(By.xpath("//*[@id='tblResultProduct']/tbody/tr")).size();
        for (int i = 0; i < rows; i++) {
            WebElement row = sbc.getDriver().findElement(By.id(String.valueOf(i)));
            double resultTOut = Double.parseDouble(row.getAttribute("data-airtemperatureoutlet"));
            if (resultTOut >= tOut) {
                double capacity = Double.parseDouble(row.getAttribute("data-capacity")) / 1000;
                long airDrop = Math.round(Double.parseDouble(row.getAttribute("data-airpressuredrop")));
                double airVelocity = Double.parseDouble(row.getAttribute("data-airvelocity"));
                String fluidDrop = Math.scalb(Double.parseDouble(row.getAttribute("data-fldpressuredrop")), 2) + getTextByXPath("//span[contains(@class, 'clsLiquidPresDrop')]/br");
                String fluidFlow = Math.scalb(Double.parseDouble(row.getAttribute("data-fldpressuredrop")), 2) + getTextByXPath("//span[contains(@class, 'clsLiquidFlow')]/br");
                String model = row.getAttribute("data-model");
                return new Result(capacity, tOut, airDrop, airVelocity, fluidDrop, fluidFlow, model);
            }
        }
        return null;
    }

    private String getTextByXPath(String xpath) {
        return sbc.getDriver().findElement(By.xpath(xpath)).getText();
    }

    private void waitResult() {
        sbc.getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("0")));
    }
}
