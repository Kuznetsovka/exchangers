package com.systemair.exchangers.domain.exchangers.models;

import com.systemair.exchangers.domain.Process;
import com.systemair.exchangers.domain.TypeMontage;
import com.systemair.exchangers.domain.exchangers.Brand;
import com.systemair.exchangers.domain.exchangers.Heater;
import com.systemair.exchangers.myInterface.Describable;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

import static com.systemair.exchangers.domain.exchangers.Brand.VEAB;

@AllArgsConstructor
public class RoundHeater extends Heater {
    public NameModel model;
    public static final String URL = "http://calculation.veab.com/ru-RU/Calculation/Index/CWW/H";

    public RoundHeater() {
        this.typeMontage = TypeMontage.ROUND;
    }

    @Getter
    protected enum NameModel implements Describable {
        CWW_100_2("CWW 100-2-2,5", "VBC 100-2-2,5"),
        CWW_125_2("CWW 125-2-2,5", "VBC 125-2-2,5"),
        CWW_160_2("CWW 160-2-2,5", "VBC 160-2-2,5"),
        CWW_200_2("CWW 200-2-2,5", "VBC 200-2-2,5"),
        CWW_250_2("CWW 250-2-2,5", "VBC 250-2-2,5"),
        CWW_315_2("CWW 315-2-2,5", "VBC 315-2-2,5"),
        CWW_400_2("CWW 400-2-2,5", "VBC 400-2-2,5"),
        CWW_500_2("CWW 500-2-2,5", "VBC 500-2-2,5"),
        CWW_100_3("CWW 100-3-2,5", "VBC 100-3-2,5"),
        CWW_125_3("CWW 125-3-2,5", "VBC 125-3-2,5"),
        CWW_160_3("CWW 160-3-2,5", "VBC 160-3-2,5"),
        CWW_300_3("CWW 200-3-2,5", "VBC 200-3-2,5"),
        CWW_350_3("CWW 250-3-2,5", "VBC 250-3-2,5"),
        CWW_315_3("CWW 315-3-2,5", "VBC 315-3-2,5"),
        CWW_400_3("CWW 400-3-2,5", "VBC 400-3-2,5");
        private final String value;
        private final String modelSystemair;

        NameModel(String value, String modelSystemair) {
            this.value = value;
            this.modelSystemair = modelSystemair;
        }


        public static NameModel getByDescription(String modelVeab) {
            for (NameModel desc : NameModel.values()) {
                if (Objects.requireNonNull(desc.getTxt()).equals(modelVeab)) {
                    return desc;
                }
            }
            throw new IllegalArgumentException("Тип не соответствует доступным значениям!");
        }

        public static NameModel getBySystemairName(String modelSystemair) {
            for (NameModel desc : NameModel.values()) {
                if (Objects.requireNonNull(desc.getModelSystemair()).equals(modelSystemair)) {
                    return desc;
                }
            }
            throw new IllegalArgumentException("Тип не соответствует доступным значениям!");
        }

        @Override
        public String toString() {
            return "NameModel{" +
                    "value=" + value + '\'' +
                    '}';
        }

        @Override
        public String getTxt() {
            return this.value;
        }
    }

    @Override
    public String getURL() {
        return URL;
    }

    public Process getProcess() {
        return this.process;
    }

    @Override
    public void setModel(String model, Brand brand) {
        if (brand == VEAB)
            this.model = NameModel.getByDescription(model);
        else
            this.model = NameModel.getBySystemairName(model);
    }

    @Override
    public String getModel() {
        return (model != null) ? model.getTxt() : "";
    }

    @Override
    public String getModelSystemair() {
        return (model != null) ? this.model.getModelSystemair() : "";
    }

    @Override
    public String toString() {
        return "RoundHeater{" +
                "tIn=" + tIn +
                ", uIn=" + uIn +
                ", airFlow=" + airFlow +
                ", fluid=" + fluid +
                ", tOut=" + tOut +
                ", typeMontage=" + typeMontage +
                ", result=" + result +
                ", type=" + process.getTxt() +
                ", model=" + model +
                '}';
    }

}
