package com.systemair.exchangers.domain.fluid;

import com.systemair.exchangers.myInterface.Describable;
import lombok.Getter;

import java.util.Objects;

@Getter
public class Water extends Fluid {
    private final TypeWater type;
    private final Integer mixture;
    private final int tOutFluid;

    public Water(TypeWater type, Integer mixture, int tInFluid, int tOutFluid) {
        this.mixture = mixture;
        this.tInFluid = tInFluid;
        this.tOutFluid = tOutFluid;
        this.type = type;
    }

    @Getter
    public enum TypeWater implements Describable<TypeWater> {
        WATER("Вода", 0),
        ETHYLENE_GLYCOL("Этиленгликоль", 1),
        PROPYLENE_GLYCOL("Пропиленгликоль", 2);
        private final String description;
        private final int value;

        TypeWater(String desc, int value) {
            this.description = desc;
            this.value = value;
        }

        public static TypeWater getByDescription(String description) {
            for (TypeWater desc : TypeWater.values()) {
                if (Objects.requireNonNull(desc.getTxt()).equals(description)) {
                    return desc;
                }
            }
            throw new IllegalArgumentException("Тип не соответствует доступным значениям!");
        }

        @Override
        public String getTxt() {
            return this.description;
        }
    }

    @Override
    public String toString() {
        return "Water{" +
                "type=" + type +
                ", mixture=" + mixture +
                ", tInFluid=" + tInFluid +
                ", tOutFluid=" + tOutFluid +
                '}';
    }
}
