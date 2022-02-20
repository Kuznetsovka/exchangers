package com.systemair.exchangers.domain.fluid;

import lombok.Getter;

import java.util.Arrays;

@Getter
public class Freon extends Fluid {
    private final TypeFreon type;

    public Freon(TypeFreon type) {
        this.type = type;
    }
    @Getter
    public enum TypeFreon {
        R410A(34),
        R134A(19),
        R407C(30),
        R32(66);
        private final int value;

        TypeFreon(int value) {
            this.value = value;
        }

        public static boolean isFreon(String txt){
            return Arrays.stream(TypeFreon.values()).anyMatch(f -> f.toString().equals(txt));
        }
    }
}
