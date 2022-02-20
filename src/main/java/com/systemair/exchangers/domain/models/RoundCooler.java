package com.systemair.exchangers.domain.models;

import lombok.Getter;

import static com.systemair.exchangers.domain.Process.COOL;

@Getter
public class RoundCooler extends Model {
    private final String type = COOL.getTxt();
    private NameModel model;
    @Getter
    private enum NameModel {
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
    }
}
