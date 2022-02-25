package com.systemair.exchangers.domain.fluid;

import com.systemair.exchangers.myInterface.Describable;
import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;

@Getter
public class Freon extends Fluid {
    private final TypeFreon type;

    public Freon(TypeFreon type, int tInFluid) {
        this.type = type;
        this.tInFluid = tInFluid;
    }

    @Getter
    public enum TypeFreon implements Describable, TypeFluid {
        R410A(34),
        R134A(19),
        R407C(30),
        R32(66);
        private final int value;

        TypeFreon(int value) {
            this.value = value;
        }

        public static boolean isFreon(String txt) {
            return Arrays.stream(TypeFreon.values()).anyMatch(f -> f.toString().equals(txt));
        }

        public static TypeFreon getByDescription(String description) {
            for (TypeFreon desc : TypeFreon.values()) {
                if (Objects.requireNonNull(desc.getTxt()).equals(description)) {
                    return desc;
                }
            }
            throw new IllegalArgumentException("Тип не соответствует доступным значениям!");
        }

        @Override
        public String getTxt() {
            return String.valueOf(this.value);
        }
    }

    @Override
    public String toString() {
        return "Freon{" +
                "tInFluid=" + tInFluid +
                ", type=" + type +
                '}';
    }
}
