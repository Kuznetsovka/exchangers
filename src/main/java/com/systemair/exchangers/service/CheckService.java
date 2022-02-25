package com.systemair.exchangers.service;

public interface CheckService {

    void checkAvailableTypeArgs(String[] args);

    default boolean isPositiveNumber(String arg) {
        return arg.matches("\\d+(\\.\\d+)?");
    }

    default boolean isNumber(String arg) {
        return arg.matches("-?\\d+(\\.\\d+)?");
    }

    boolean notAvailableTypeProcessByFluid(String process, String fluid);

    boolean notAvailableTypeProcess(String txt);

    boolean notAvailableTypeFluid(String typeMontage, String fluid);

    boolean notAvailableExchanger(String typeMontage);

    void checkArgs(String[] args);
}
