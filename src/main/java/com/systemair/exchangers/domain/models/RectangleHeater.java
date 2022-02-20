package com.systemair.exchangers.domain.models;

import lombok.Getter;

import static com.systemair.exchangers.domain.Process.HEAT;

@Getter
public class RectangleHeater extends Model {
    private final String type = HEAT.getTxt();
    private NameModel model;

    private enum NameModel {
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

    }
}
