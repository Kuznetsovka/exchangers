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

    void calculation(String txt);
}
