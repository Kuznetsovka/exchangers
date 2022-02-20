package com.systemair.exchangers.domain.fluid;

import lombok.Getter;

@Getter
public class Water extends Fluid {
    private TypeWater type;
    private Integer mixture;
    private int tOutFluid;
    public Water(TypeWater type,Integer mixture, int tOutFluid) {
        this.mixture = mixture;
        this.tOutFluid = tOutFluid;
        this.type = type;
    }
    @Getter
    private enum TypeWater {
        WATER("Вода", 0),
        ETHYLENE_GLYCOL("Этиленгликоль", 1),
        PROPYLENE_GLYCOL("Пропиленгликоль", 2);
        private final String description;
        private final int value;

        TypeWater(String desc, int value) {
            this.description = desc;
            this.value = value;
        }
    }

}
