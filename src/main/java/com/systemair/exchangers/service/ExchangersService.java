package com.systemair.exchangers.service;

import com.systemair.exchangers.domain.Process;
import com.systemair.exchangers.domain.exchangers.Exchanger;

import java.util.ArrayList;

public interface ExchangersService {
    Exchanger getExchanger(String[] args);
    Exchanger getExchanger(ArrayList<String> row, Process process);
}
