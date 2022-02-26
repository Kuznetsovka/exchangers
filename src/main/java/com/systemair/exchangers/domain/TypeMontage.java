package com.systemair.exchangers.domain;

import com.systemair.exchangers.myInterface.Describable;

public enum TypeMontage implements Describable {
    ROUND("Круглый"),
    RECTANGLE("Прямоугольный");
    private final String description;

    TypeMontage(String desc) {
        this.description = desc;
    }

    @Override
    public String getTxt() {
        return this.description;
    }

    @Override
    public String toString() {
        return this.description;
    }
}
