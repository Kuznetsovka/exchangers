package com.systemair.exchangers.service;

import static com.systemair.exchangers.domain.Process.COOL;
import static com.systemair.exchangers.domain.Process.HEAT;
import static com.systemair.exchangers.domain.TypeMontage.RECTANGLE;
import static com.systemair.exchangers.domain.TypeMontage.ROUND;
import static com.systemair.exchangers.domain.fluid.Freon.TypeFreon.isFreon;

public class CheckServiceImpl implements CheckService {
    public  void checkArgs(String[] args) {
        if (args.length != 10 && args.length != 11)
            throw new IndexOutOfBoundsException("Количество аргументов не соответствует протоколу!");
        if (notAvailableExchanger(args[0])) {
            throw new IndexOutOfBoundsException("Тип подключения не доступен для подбора теплообменника!");
        }
        if (notAvailableTypeProcess(args[1])) {
            throw new IndexOutOfBoundsException(String.format("Тип процесса %s не допустим!", args[1]));
        }
        if (notAvailableTypeProcessByFluid(args[1], args[2])) {
            throw new IndexOutOfBoundsException(String.format("Тип жидкости %s не соответствует процессу %s!", args[2], args[1]));
        }
        if (notAvailableTypeFluid(args[0], args[2])) {
            throw new IndexOutOfBoundsException(String.format("Тип жидкости %s не возможен для типа подключения %s!", args[2], args[0]));
        }
        if (notAvailableTypeFluid(args[0], args[2])) {
            throw new IndexOutOfBoundsException(String.format("Тип жидкости %s не возможен для типа подключения %s!", args[1], args[0]));
        }
        checkAvailableTypeArgs(args);
        //TODO Проверка совпадения модели с типом теплообменника
    }

    public void checkAvailableTypeArgs(String[] args) {
        for (int i = 3; i < 10; i++) {
            if (!isNumber(args[i])) {
                throw new IllegalArgumentException(String.format("Аргумент %s не является числом!", args[i]));
            }
            if (i != 4 || !args[1].equals(HEAT.getTxt()))
                if (!isPositiveNumber(args[i])) {
                    throw new IllegalArgumentException(String.format("Аргумент %s не является положительным числом!", args[i]));
                }
        }
    }

    public boolean isPositiveNumber(String arg) {
        return arg.matches("\\d+(\\.\\d+)?");
    }

    public boolean isNumber(String arg) {
        return arg.matches("-?\\d+(\\.\\d+)?");
    }

    public boolean notAvailableTypeProcessByFluid(String process, String fluid) {
        if (process.equals(HEAT.getTxt())) {
            return isFreon(fluid);
        }
        return false;
    }

    public boolean notAvailableTypeProcess(String txt) {
        return !txt.equals(HEAT.getTxt()) && !txt.equals(COOL.getTxt());
    }

    public boolean notAvailableTypeFluid(String typeMontage, String fluid) {
        if (typeMontage.equals(ROUND.getTxt())) {
            return isFreon(fluid);
        }
        return false;
    }

    public boolean notAvailableExchanger(String typeMontage){
        return !typeMontage.equals(ROUND.getTxt()) && !typeMontage.equals(RECTANGLE.getTxt());
    }
}
