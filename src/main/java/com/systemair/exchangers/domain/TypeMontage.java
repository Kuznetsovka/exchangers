package com.systemair.exchangers.domain;

import com.systemair.exchangers.myInterface.Describable;

public enum TypeMontage implements Describable<TypeMontage> {
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

    public static TypeMontage getByDescription(String description) {
        for (TypeMontage desc : TypeMontage.values()) {
            if (desc.getTxt().equals(description)) {
                return desc;
            }
        }
        throw new IllegalArgumentException("Тип монтажа не соответствует доступным значениям!");
    }

    @Override
    public String toString() {
        return this.description;
    }
}
