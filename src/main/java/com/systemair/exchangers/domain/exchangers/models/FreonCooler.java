package com.systemair.exchangers.domain.exchangers.models;

import com.systemair.exchangers.domain.Process;
import com.systemair.exchangers.domain.TypeMontage;
import com.systemair.exchangers.domain.exchangers.Cooler;
import com.systemair.exchangers.myInterface.Describable;
import lombok.AllArgsConstructor;

import java.util.Objects;

import static com.systemair.exchangers.domain.exchangers.models.FreonCooler.NameModel.getByDescription;

@AllArgsConstructor
public class FreonCooler extends Cooler {
    private NameModel model;
    public static final String URL = "http://calculation.veab.com/en-US/Calculation/Index/PGDX/HC";

    public FreonCooler() {
        this.typeMontage = TypeMontage.RECTANGLE;
    }

    public enum NameModel implements Describable {
        PGDX_400x200_3("PGDX 400x200-3-2,5", "DXRE 40-20-3"),
        PGDX_500x250_3("PGDX 500x250-3-2,5", "DXRE 50-25-3"),
        PGDX_500x300_3("PGDX 500x300-3-2,5", "DXRE 50-30-3"),
        PGDX_500x400_3("PGDX 500x400-3-2,5", "DXRE 50-40-3"),
        PGDX_600x300_3("PGDX 600x300-3-2,5", "DXRE 60-30-3"),
        PGDX_600x350_3("PGDX 600x350-3-2,5", "DXRE 60-35-3"),
        PGDX_700x400_3("PGDX 700x400-3-2,5", "DXRE 70-40-3"),
        PGDX_800x400_3("PGDX 800x400-3-2,5", "DXRE 80-40-3"),
        PGDX_800x500_3("PGDX 800x500-3-2,5", "DXRE 80-50-3"),
        PGDX_1000x500_3("PGDX 1000x500-3-2,5", "DXRE 100-50-3"),
        PGDX_1200x600_3("PGDX 1200x600-3-2,5", "DXRE 120-60-3");
        private final String value;
        private final String modelSystemair;
        NameModel(String value, String modelSystemair) {
            this.value = value;
            this.modelSystemair = modelSystemair;
        }

        @Override
        public String getModelSystemair() {
            return this.modelSystemair;
        }

        @Override
        public String getTxt() {
            return this.value;
        }

        public static NameModel getByDescription(String modelVeab) {
            for (NameModel desc : NameModel.values()) {
                if (Objects.requireNonNull(desc.getTxt()).equals(modelVeab)) {
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
    }

    @Override
    public void setModel(String model) {
        this.model = NameModel.valueOf(model);
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
    public String getModelByVeabModel(String model) {
        return getByDescription(model).modelSystemair;
    }

    @Override
    public String toString() {
        return "FreonCooler{" +
                "tIn=" + tIn +
                ", uIn=" + uIn +
                ", airFlow=" + airFlow +
                ", fluid=" + fluid +
                ", typeMontage=" + typeMontage +
                ", result=" + result +
                ", type='" + process.getTxt() + '\'' +
                ", model=" + model +
                '}';
    }

}
