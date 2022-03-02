package com.systemair.exchangers.domain.exchangers.models;

import com.systemair.exchangers.domain.Process;
import com.systemair.exchangers.domain.TypeMontage;
import com.systemair.exchangers.domain.exchangers.Cooler;
import com.systemair.exchangers.myInterface.Describable;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FreonCooler extends Cooler {
    private NameModel model;
    public static final String URL = "http://calculation.veab.com/en-US/Calculation/Index/PGDX/HC";

    public FreonCooler() {
        this.typeMontage = TypeMontage.RECTANGLE;
    }

    public enum NameModel implements Describable {
        PGDX_400x200_3("PGDX 400x200-3-2,5"),
        PGDX_500x250_3("PGDX 500x250-3-2,5"),
        PGDX_500x300_3("PGDX 500x300-3-2,5"),
        PGDX_500x400_3("PGDX 500x400-3-2,5"),
        PGDX_600x300_3("PGDX 600x300-3-2,5"),
        PGDX_600x350_3("PGDX 600x350-3-2,5"),
        PGDX_700x400_3("PGDX 700x400-3-2,5"),
        PGDX_800x400_3("PGDX 800x400-3-2,5"),
        PGDX_800x500_3("PGDX 800x500-3-2,5"),
        PGDX_1000x500_3("PGDX 1000x500-3-2,5"),
        PGDX_1200x600_3("PGDX 1200x600-3-2,5");
        private final String value;

        NameModel(String value) {
            this.value = value;
        }

        @Override
        public String getTxt() {
            return this.value;
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
