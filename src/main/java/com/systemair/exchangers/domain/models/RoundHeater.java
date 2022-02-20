package com.systemair.exchangers.domain.models;

import lombok.Getter;

import static com.systemair.exchangers.domain.Process.HEAT;

@Getter
public class RoundHeater extends Model {
    private final String type = HEAT.getTxt();
    private NameModel model;
    @Getter
    private enum NameModel {
        CWW_100_2("CWW 100-2-2,5"),
        CWW_125_2("CWW 125-2-2,5"),
        CWW_160_2("CWW 160-2-2,5"),
        CWW_200_2("CWW 200-2-2,5"),
        CWW_250_2("CWW 250-2-2,5"),
        CWW_315_2("CWW 315-2-2,5"),
        CWW_400_2("CWW 400-2-2,5"),
        CWW_500_2("CWW 500-2-2,5"),
        CWW_100_3("CWW 100-3-2,5"),
        CWW_125_3("CWW 125-3-2,5"),
        CWW_160_3("CWW 160-3-2,5"),
        CWW_300_3("CWW 200-3-2,5"),
        CWW_350_3("CWW 250-3-2,5"),
        CWW_315_3("CWW 315-3-2,5"),
        CWW_400_3("CWW 400-3-2,5");
        private final String value;
        NameModel(String value) {
            this.value = value;
        }
    }
}
