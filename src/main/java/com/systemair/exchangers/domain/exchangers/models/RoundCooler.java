package com.systemair.exchangers.domain.exchangers.models;

import com.systemair.exchangers.domain.Process;
import com.systemair.exchangers.domain.TypeMontage;
import com.systemair.exchangers.domain.exchangers.Brand;
import com.systemair.exchangers.domain.exchangers.Cooler;
import com.systemair.exchangers.myInterface.Describable;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

import static com.systemair.exchangers.domain.exchangers.Brand.VEAB;


@AllArgsConstructor
public class RoundCooler extends Cooler {
    private NameModel model;
    public static final String URL = "http://calculation.veab.com/ru-RU/Calculation/Index/CWK/HC";

    public RoundCooler() {
        this.typeMontage = TypeMontage.ROUND;
    }

    @Getter
    protected enum NameModel implements Describable {
        CWK_100_3("CWK 100-3-2,5", "CWK 100-3-2,5"),
        CWK_125_3("CWK 125-3-2,5", "CWK 125-3-2,5"),
        CWK_160_3("CWK 160-3-2,5", "CWK 160-3-2,5"),
        CWK_300_3("CWK 200-3-2,5", "CWK 200-3-2,5"),
        CWK_350_3("CWK 250-3-2,5", "CWK 250-3-2,5"),
        CWK_315_3("CWK 315-3-2,5", "CWK 315-3-2,5"),
        CWK_400_3("CWK 400-3-2,5", "CWK 400-3-2,5");
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
    public String getURL() {
        return URL;
    }

    public Process getProcess() {
        return this.process;
    }

    @Override
    public String getModelSystemair() {
        return (model != null) ? this.model.getModelSystemair() : "";
    }

    @Override
    public String toString() {
        return "RoundCooler{" +
                "tIn=" + tIn +
                ", uIn=" + uIn +
                ", airFlow=" + airFlow +
                ", fluid=" + fluid +
                ", tOut=" + tOut +
                ", typeMontage=" + typeMontage +
                ", result=" + result +
                ", type=" + process.getTxt() + '\'' +
                ", model=" + model +
                '}';
    }
}
