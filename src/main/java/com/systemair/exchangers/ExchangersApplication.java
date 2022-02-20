package com.systemair.exchangers;

import com.systemair.exchangers.domain.TypeMontage;
import com.systemair.exchangers.domain.fluid.Freon;

import java.util.Arrays;

import static com.systemair.exchangers.domain.Process.COOL;
import static com.systemair.exchangers.domain.Process.HEAT;

public class ExchangersApplication {
    /**
     * 0 - Тип монтажа
     * 1 - Тип теплообменника
     * 2 - Тип жидкости
     * 3 - процент смеси
     * 4 - Т входа
     * 5 - U входа
     * 6 - Расход
     * 7 - Т входа жидкости
     * 8 - Т выхода жидкости
     * @param args
     */
    public static void main(String[] args){
        if (args.length != 10)
            throw new IndexOutOfBoundsException("Количество аргументов не соответствует протоколу!");
        if (notAvailableExchanger(args[0])){
            throw new IndexOutOfBoundsException("Тип подключения не доступен для подбора теплообменника!");
        }
        if (notAvailableTypeProcess(args[1])){
            throw new IndexOutOfBoundsException(String.format("Тип процесса %s не допустим!",args[1]));
        }
        if (notAvailableTypeProcessByFluid(args[1],args[2])){
            throw new IndexOutOfBoundsException(String.format("Тип жидкости %s не соответствует процессу %s!",args[2],args[1]));
        }
        if (notAvailableTypeFluid(args[0],args[2])){
            throw new IndexOutOfBoundsException(String.format("Тип жидкости %s не возможен для типа подключения %s!",args[2],args[0]));
        }
        if (notAvailableTypeFluid(args[0],args[2])){
            throw new IndexOutOfBoundsException(String.format("Тип жидкости %s не возможен для типа подключения %s!",args[1],args[0]));
        }
        checkAvailableTypeArgs(args);
    }

    private static void checkAvailableTypeArgs(String[] args) {
        for (int i = 3; i < args.length; i++) {
            if(!isNumber(args[i])){
                throw new IllegalArgumentException(String.format("Аргумент %s не является числом!",args[i]));
            }
            if(!isPositiveNumber(args[i])){
                throw new IllegalArgumentException(String.format("Аргумент %s не является положительным числом!",args[i]));
            }
        }
    }

    private static boolean isPositiveNumber(String arg) {
        return arg.matches("\\d+(\\.\\d+)?");
    }

    private static boolean isNumber(String arg) {
        return arg.matches("-?\\d+(\\.\\d+)?");
    }

    private static boolean notAvailableTypeProcessByFluid(String process, String fluid) {
        if (process.equals(HEAT.getTxt())) {
            return Freon.TypeFreon.isFreon(fluid);
        }
        return false;
    }

    private static boolean notAvailableTypeProcess(String txt) {
        return !txt.equals(HEAT.getTxt()) && !txt.equals(COOL.getTxt());
    }

    private static boolean notAvailableTypeFluid(String typeMontage, String fluid) {
        if (typeMontage.equals(TypeMontage.ROUND.getTxt())) {
            return Freon.TypeFreon.isFreon(fluid);
        }
        return false;
    }

    private static boolean notAvailableExchanger(String typeMontage){
        return !typeMontage.equals(TypeMontage.ROUND.getTxt()) && !typeMontage.equals(TypeMontage.RECTANGLE.getTxt());
    }
}