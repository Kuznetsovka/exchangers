package com.systemair.exchangers.domain.exchangers.models;

import com.systemair.exchangers.domain.Process;
import com.systemair.exchangers.domain.TypeMontage;
import com.systemair.exchangers.domain.exchangers.Heater;
import com.systemair.exchangers.myInterface.Describable;
import lombok.AllArgsConstructor;

import java.util.Objects;

@AllArgsConstructor
public class RectangleHeater extends Heater {
    public final String URL = "http://calculation.veab.com/ru-RU/Calculation/Index/PGV/H";
    private NameModel model;

    public RectangleHeater() {
        this.typeMontage = TypeMontage.RECTANGLE;
    }

    @Override
    public void setModel(String model) {
        this.model = NameModel.valueOf(model);
    }

    private enum NameModel implements Describable {
        PGV_250x150_2("PGV 250x150-2-2,5"),
        PGV_400x200_2("PGV 400x200-2-2,5"),
        PGV_400x200_4("PGV 400x200-4-2,5"),
        PGV_500X250_2("PGV 500X250-2-2,5"),
        PGV_500X250_4("PGV 500X250-4-2,5"),
        PGV_500X300_2("PGV 500X300-2-2,5"),
        PGV_500X300_4("PGV 500X300-4-2,5"),
        PGV_500x400_2("PGV 500x400-2-2,5"),
        PGV_500x400_4("PGV 500x400-4-2,5"),
        PGV_600X300_2("PGV 600X300-2-2,5"),
        PGV_600X300_4("PGV 600X300-4-2,5"),
        PGV_600X350_2("PGV 600X350-2-2,5"),
        PGV_600X350_4("PGV 600X350-4-2,5"),
        PGV_700X400_2("PGV 700X400-2-2,5"),
        PGV_700X400_3("PGV 700X400-3-2,5"),
        PGV_800x400_2("PGV 800x400-2-2,5"),
        PGV_800x400_3("PGV 800x400-3-2,5"),
        PGV_800X500_2("PGV 800X500-2-2,5"),
        PGV_800X500_3("PGV 800X500-3-2,5"),
        PGV_1000X500_2("PGV 1000X500-2-2,5"),
        PGV_1000X500_3("PGV 1000X500-3-2,5"),
        PGV_1200X600_2("PGV 1200X600-2-2,5"),
        PGV_1200X600_3("PGV 1200X600-3-2,5");
        private final String value;

        NameModel(String value) {
            this.value = value;
        }

        public static NameModel getByDescription(String description) {
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

    public Process getProcess() {
        return this.process;
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
                ", type='" + process.getTxt() + '\'' +
                ", model=" + model +
                '}';
    }

//    public NameModel getModel() {
//        return model;
//    }
}
