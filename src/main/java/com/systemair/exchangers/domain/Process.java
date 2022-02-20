package com.systemair.exchangers.domain;

import com.systemair.exchangers.myInterface.Describable;

public enum Process implements Describable<Process> {
    HEAT("Нагрев"),
    COOL("Охлаждение");
    private final String description;

    Process(String desc) {
        this.description = desc;
    }

    @Override
    public String getTxt() {
        return this.description;
    }


    public static Process getByDescription(String description) {
        for (Process desc : Process.values()) {
            if (desc.getTxt().equals(description)) {
                return desc;
            }
        }
        throw new IllegalArgumentException("Тип процесса не соответствует доступным значениям!");
    }

    @Override
    public String toString() {
        return this.description;
    }
}
