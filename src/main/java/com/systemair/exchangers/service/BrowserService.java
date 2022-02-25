package com.systemair.exchangers.service;

import com.systemair.exchangers.domain.Process;
import com.systemair.exchangers.domain.exchangers.Exchanger;
import com.systemair.exchangers.domain.exchangers.Result;

public interface BrowserService {
    void navigate(String url);

    void selectModel(String model);

    void stop();

    void changeValueComboBoxByLabel(String id, String newValue);

    void fillTechData(Exchanger exchanger);

    void inputTextById(String id, String newValue) throws InterruptedException;

    Result getResult(Process process, int tOut);
//
//    void initializeBrowser();
//
//    void setNegativeLimit(String negativeLimit);
//
//    void setPositiveLimit(String positiveLimit);
//
//    void onCheckbox(boolean onAction, WebElement webElement);
//
//    void onCheckboxDiagram(WebElement webElement);
//
//    void prepareStartPageBeforeCalculation();
//
//    void inputTextByLabel(String findTextLabel, String newValue) throws InterruptedException;
//
//    void fillFlowAndDrop(String airFlow, String airDrop);
//
//    void changeMeasureValueOnTableByIndex(String newValue, int index);
//
//    SingletonBrowserClass getSbc();
}
