package com.systemair.exchangers.domain.models;

import com.systemair.exchangers.domain.TypeMontage;
import com.systemair.exchangers.domain.exchangers.Cooler;
import com.systemair.exchangers.myInterface.Describable;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

import static com.systemair.exchangers.domain.Process.COOL;

@AllArgsConstructor
public class RectangleCooler extends Cooler {
    private final String type = COOL.getTxt();
    private NameModel model;
    public static final String URL = "http://calculation.veab.com/en-US/Calculation/Index/PGK/H";

    public RectangleCooler() {
        this.typeMontage = TypeMontage.RECTANGLE;
    }

    @Getter
    private enum NameModel implements Describable {
        PGK_400x200_3("PGK 400x200-3-2,0"),
        PGK_500x250_3("PGK 500x250-3-2,0"),
        PGK_500X300_3("PGK 500X300-3-2,0"),
        PGK_500x400_3("PGK 500x400-3-2,0"),
        PGK_600X300_3("PGK 600X300-3-2,0"),
        PGK_600X350_3("PGK 600X350-3-2,0"),
        PGK_700X400_3("PGK 700X400-3-2,0"),
        PGK_800x400_3("PGK 800x400-3-2,0"),
        PGK_800X500_3("PGK 800X500-3-2,0"),
        PGK_1000X500_3("PGK 1000X500-3-2,0"),
        PGK_1200X600_3("PGK 1200X600-3-2,0"),
        PGK_250X150_4("PGK 250X150-4-2,0"),
        PGK_400x200_4("PGK 400x200-4-2,0"),
        PGK_500x250_4("PGK 500x250-4-2,0"),
        PGK_500x300_4("PGK 500x300-4-2,0"),
        PGK_500x400_4("PGK 500x400-4-2,0"),
        PGK_600x300_4("PGK 600x300-4-2,0"),
        PGK_600x350_4("PGK 600x350-4-2,0"),
        PGK_700x400_4("PGK 700x400-4-2,0"),
        PGK_800x400_4("PGK 800x400-4-2,0"),
        PGK_800x500_4("PGK 800x500-4-2,0"),
        PGK_1000x500_4("PGK 1000x500-4-2,0");
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
        public String getTxt() {
            return this.value;
        }
    }

    @Override
    public String getURL() {
        return URL;
    }

//    public NameModel getModel() {
//        return model;
//    }
}
