package com.systemair.exchangers.service;

import com.systemair.exchangers.domain.Browser;
import com.systemair.exchangers.domain.Process;
import com.systemair.exchangers.domain.exchangers.Exchanger;
import com.systemair.exchangers.domain.exchangers.Result;
import com.systemair.exchangers.domain.exchangers.Value;
import com.systemair.exchangers.domain.fluid.Water;
import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.systemair.exchangers.domain.Process.COOL;
import static com.systemair.exchangers.domain.Process.HEAT;
import static com.systemair.exchangers.domain.fluid.Freon.TypeFreon.isFreon;

public class VeabBrowserService extends BrowserServiceImpl {
    private String secondWindow = "";

    @Override
    public void navigate(String url) {
        if (secondWindow.isEmpty())
            browser.getDriver().switchTo().newWindow(WindowType.TAB);
        secondWindow = browser.getDriver().getWindowHandle();
        browser.getDriver().navigate().to(url);
        //TODO Ожижание переключения
    }

    @Override
    public void selectModel(String model) {
        changeValueComboBoxByLabel("selSingleSelection", model);
    }


    @Override
    public void fillTechData(Exchanger exchanger) {
        if (exchanger.getProcess() == COOL)
            changeValueComboBoxByLabel("selCalculationMethod", "Cooling");
        inputTextById("txtInletDryBulbTemperature", String.valueOf(exchanger.getTIn()));
        inputTextById("txtRelativeHumidity", String.valueOf(exchanger.getUIn()));
        inputTextById("txtVolumetricFlowAir", String.valueOf(exchanger.getAirFlow()));
        changeValueComboBoxByLabel("selFluid", String.valueOf(exchanger.getFluid().getType().getValue()));
        if (!isFreon(exchanger.getFluid().getType().getTxt())) {
            inputTextById("txtInletTemperature", String.valueOf(exchanger.getFluid().getTInFluid()));
            inputTextById("txtOutletTemparature", String.valueOf(exchanger.getFluid().getTOutFluid()));//Ошибка id в DOM, не менять
            if (!exchanger.getFluid().getType().getTxt().equals(Water.TypeWater.WATER.getTxt())) {
                inputTextById("txtMixtureRate", String.valueOf(exchanger.getFluid().getMixture()));
            }
        }
        inputTextById("txtAirOutletTemperature", String.valueOf(exchanger.getTOut()));
        inputTextById("txtTolerance", String.valueOf(30));
    }

    @Override
    public Result getResult(Process process, int tOut) {
        int rows = browser.getDriver().findElements(By.xpath("//*[@id='tblResultProduct']/tbody/tr")).size();
        for (int i = 0; i < rows; i++) {
            WebElement row = browser.getDriver().findElement(By.id(String.valueOf(i)));
            double resultTOut = Double.parseDouble(row.getAttribute("data-airtemperatureoutlet"));
            if (process == HEAT) {
                if (resultTOut >= tOut) return getResult(row);
            } else {
                if (resultTOut <= tOut) return getResult(row);
            }
        }
        return null;
    }

    private Result getResult(WebElement row) {
        double capacity = Double.parseDouble(row.getAttribute("data-capacity")) / 1000;
        double tOut = Double.parseDouble(row.getAttribute("data-airtemperatureoutlet"));
        long airDrop = Math.round(Double.parseDouble(row.getAttribute("data-airpressuredrop")));
        double airVelocity = Double.parseDouble(row.getAttribute("data-airvelocity"));
        double fluidDrop = Double.parseDouble(row.getAttribute("data-fldpressuredrop"));
        String measureFluidDrop = getTextByXPath("//span[contains(@class, 'clsLiquidPresDrop')]");
        double fluidFlow = Double.parseDouble(row.getAttribute("data-fldvolumetricflowinlet"));
        String measureFluidFlow = getTextByXPath("//span[contains(@class, 'clsLiquidFlow')]");
        String model = row.getAttribute("data-model");
        return new Result(
                new Value(capacity, "kW", 2),
                new Value(tOut, "", 1),
                new Value(airDrop, "Pa", 0),
                new Value(airVelocity, "m/s", 2),
                new Value(fluidDrop, measureFluidDrop, 2),
                new Value(fluidFlow, measureFluidFlow, 3),
                model);
    }

    @SneakyThrows
    @Override
    public void calculation(String model) {
        if (model.isEmpty())
            clickElementIfExistsByXpath("//*[@id='Data']/div[13]/button");
        else
            selectModel(model);
        waitResult();
    }

    @Override
    public void setBrowser(Browser browser) {
        this.browser = browser;
    }

    private String getTextByXPath(String xpath) {
        return browser.getDriver().findElement(By.xpath(xpath)).getText();
    }

    private void waitResult() {
        browser.getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("0")));
    }
}
