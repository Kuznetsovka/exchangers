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
public class RectangleCooler extends Cooler {
    private NameModel model;
    public static final String URL = "http://calculation.veab.com/en-US/Calculation/Index/PGK/H";

    public RectangleCooler() {
        this.typeMontage = TypeMontage.RECTANGLE;
    }

    @Getter
    protected enum NameModel implements Describable {
        PGK_400x200_3("PGK 400x200-3-2,0", "PGK 40-20-3"),
        PGK_500x250_3("PGK 500x250-3-2,0", "PGK 50-25-3"),
        PGK_500X300_3("PGK 500X300-3-2,0", "PGK 50-30-3"),
        PGK_500x400_3("PGK 500x400-3-2,0", "PGK 50-40-3"),
        PGK_600X300_3("PGK 600X300-3-2,0", "PGK 60-30-3"),
        PGK_600X350_3("PGK 600X350-3-2,0", "PGK 60-35-3"),
        PGK_700X400_3("PGK 700X400-3-2,0", "PGK 70-40-3"),
        PGK_800x400_3("PGK 800x400-3-2,0", "PGK 80-40-3"),
        PGK_800X500_3("PGK 800X500-3-2,0", "PGK 80-50-3"),
        PGK_1000X500_3("PGK 1000X500-3-2,0", "PGK 100-50-3"),
        PGK_1200X600_3("PGK 1200X600-3-2,0", "PGK 120-60-3"),
        PGK_250X150_4("PGK 250X150-4-2,0", "PGK 25-15-4"),
        PGK_400x200_4("PGK 400x200-4-2,0", "PGK 40-20-4"),
        PGK_500x250_4("PGK 500x250-4-2,0", "PGK 50-25-4"),
        PGK_500x300_4("PGK 500x300-4-2,0", "PGK 50-30-4"),
        PGK_500x400_4("PGK 500x400-4-2,0", "PGK 50-40-4"),
        PGK_600x300_4("PGK 600x300-4-2,0", "PGK 60-30-4"),
        PGK_600x350_4("PGK 600x350-4-2,0", "PGK 60-35-4"),
        PGK_700x400_4("PGK 700x400-4-2,0", "PGK 70-40-4"),
        PGK_800x400_4("PGK 800x400-4-2,0", "PGK 80-40-4"),
        PGK_800x500_4("PGK 800x500-4-2,0", "PGK 80-50-4"),
        PGK_1000x500_4("PGK 1000x500-4-2,0", "PGK 100-50-4");
        private final String modelVeab;
        private final String modelSystemair;

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

        NameModel(String value, String modelSystemair) {
            this.modelVeab = value;
            this.modelSystemair = modelSystemair;
        }

        @Override
        public String getTxt() {
            return this.modelVeab;
        }
    }

    public Process getProcess() {
        return this.process;
    }

    @Override
    public String getModelSystemair() {
        return (model != null) ? this.model.getModelSystemair() : "";
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
                ", type=" + process.getTxt() + '\'' +
                ", model=" + model +
                '}';
    }
}
