package com.systemair.exchangers.domain.models;

import com.systemair.exchangers.domain.TypeMontage;
import com.systemair.exchangers.domain.exchangers.Cooler;
import com.systemair.exchangers.myInterface.Describable;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

import static com.systemair.exchangers.domain.Process.COOL;


@AllArgsConstructor
public class RoundCooler extends Cooler {
    private final String type = COOL.getTxt();
    private NameModel model;
    public static final String URL = "http://calculation.veab.com/ru-RU/Calculation/Index/CWK/HC";
    public RoundCooler() {
        this.typeMontage = TypeMontage.ROUND;
    }

    @Getter
    private enum NameModel implements Describable {
        CWK_100_3("CWK 100-3-2,5"),
        CWK_125_3("CWK 125-3-2,5"),
        CWK_160_3("CWK 160-3-2,5"),
        CWK_300_3("CWK 200-3-2,5"),
        CWK_350_3("CWK 250-3-2,5"),
        CWK_315_3("CWK 315-3-2,5"),
        CWK_400_3("CWK 400-3-2,5");
        private final String value;

        NameModel(String value) {
            this.value = value;
        }

        public NameModel getByDescription(String description) {
            for (NameModel desc : NameModel.values()) {
                if (Objects.requireNonNull(desc.getTxt()).equals(description)) {
                    return desc;
                }
            }
            throw new IllegalArgumentException("Тип не соответствует доступным значениям!");
        }

        @Override
        public String toString() {
            return "NameModel{" +
                    "value='" + value + '\'' +
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

    @Override
    public String toString() {
        return "RectangleCooler{" +
                "tIn=" + tIn +
                ", uIn=" + uIn +
                ", airFlow=" + airFlow +
                ", fluid=" + fluid +
                ", tOut=" + tOut +
                ", typeMontage=" + typeMontage +
                ", result=" + result +
                ", type='" + type + '\'' +
                ", model=" + model +
                '}';
    }

//    public NameModel getModel() {
//        return model;
//    }
}
