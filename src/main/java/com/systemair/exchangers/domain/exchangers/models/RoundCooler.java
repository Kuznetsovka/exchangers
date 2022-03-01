package com.systemair.exchangers.domain.exchangers.models;

import com.systemair.exchangers.domain.Process;
import com.systemair.exchangers.domain.TypeMontage;
import com.systemair.exchangers.domain.exchangers.Cooler;
import com.systemair.exchangers.myInterface.Describable;
import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
public class RoundCooler extends Cooler {
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
    public void setModel(String model) {
        this.model = NameModel.valueOf(model);
    }

    @Override
    public String getModel() {
        return model.getTxt();
    }

    @Override
    public String getURL() {
        return URL;
    }

    public Process getProcess() {
        return this.process;
    }

    @Override
    public String toString() {
        return "RoungCooler{" +
                "tIn=" + tIn +
                ", uIn=" + uIn +
                ", airFlow=" + airFlow +
                ", fluid=" + fluid +
                ", tOut=" + tOut +
                ", typeMontage=" + typeMontage +
                ", result=" + result +
                ", type='" + process.getTxt() + '\'' +
                ", model=" + model +
                '}';
    }


//    public NameModel getModel() {
//        return model;
//    }
}
