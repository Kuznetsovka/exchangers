package com.systemair.exchangers.service;

import com.systemair.exchangers.domain.exchangers.Exchanger;

public interface ExchangersService {
    Exchanger getExchanger(String[] args);
}
