package com.systemair.exchangers;

import com.systemair.exchangers.domain.exchangers.Exchanger;
import com.systemair.exchangers.domain.exchangers.ExchangerFactory;
import com.systemair.exchangers.service.BrowserService;
import com.systemair.exchangers.service.ExchangersService;
import com.systemair.exchangers.service.ExchangersServiceImpl;
import com.systemair.exchangers.service.VeabBrowserService;

import static com.systemair.exchangers.domain.Process.COOL;
import static com.systemair.exchangers.domain.Process.HEAT;
import static com.systemair.exchangers.domain.TypeMontage.RECTANGLE;
import static com.systemair.exchangers.domain.TypeMontage.ROUND;
import static com.systemair.exchangers.domain.fluid.Freon.TypeFreon.isFreon;

public class ExchangersApplication {
    private static final ExchangerFactory exchangerFactory = new ExchangerFactory();
    static ExchangersService exchangersService = new ExchangersServiceImpl(exchangerFactory);
    static BrowserService browserService = new VeabBrowserService();

    /**
     * 0 - Тип монтажа
     * 1 - Тип теплообменника
     * 2 - Тип жидкости
     * 3 - процент смеси
     * 4 - Т входа
     * 5 - U входа
     * 6 - Т выхода
     * 7 - Расход
     * 8 - Т входа жидкости
     * 9 - Т выхода жидкости
     * 10 - Модель
     *
     * @param args
     */
    public static void main(String[] args) {
        checkArgs(args);
        Exchanger exchanger = exchangersService.getExchanger(args);
        browserService.navigate(exchanger.getURL());
        browserService.fillTechData(exchanger);
        // В конце
        //browserService.selectModel("PGV 500x400-4-2,5");
        System.out.println(exchanger);
        exchanger.setResult(browserService.getResult(exchanger.getProcess(),exchanger.getTOut()));
        System.out.println(exchanger.getResult());
        browserService.stop();
    }


    private static void checkArgs(String[] args) {
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

    private static void checkAvailableTypeArgs(String[] args) {
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

    private static boolean isPositiveNumber(String arg) {
        return arg.matches("\\d+(\\.\\d+)?");
    }

    private static boolean isNumber(String arg) {
        return arg.matches("-?\\d+(\\.\\d+)?");
    }

    private static boolean notAvailableTypeProcessByFluid(String process, String fluid) {
        if (process.equals(HEAT.getTxt())) {
            return isFreon(fluid);
        }
        return false;
    }

    private static boolean notAvailableTypeProcess(String txt) {
        return !txt.equals(HEAT.getTxt()) && !txt.equals(COOL.getTxt());
    }

    private static boolean notAvailableTypeFluid(String typeMontage, String fluid) {
        if (typeMontage.equals(ROUND.getTxt())) {
            return isFreon(fluid);
        }
        return false;
    }

    private static boolean notAvailableExchanger(String typeMontage){
        return !typeMontage.equals(ROUND.getTxt()) && !typeMontage.equals(RECTANGLE.getTxt());
    }
}