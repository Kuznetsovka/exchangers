package com.systemair.exchangers.domain.models;

import lombok.Getter;

import static com.systemair.exchangers.domain.Process.COOL;

@Getter
public class FreonCooler extends Model {
    private final String type = COOL.getTxt();
    private NameModel model;
    @Getter
    private enum NameModel {
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
    }
}
