package com.systemair.exchangers.domain.exchangers;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Value {
    private String value;
    private String measure;
    private int decimalPoint;
    private String valueWithMeasure;


    public Value(double value, String measure, int decimalPoint) {
        this.value = String.format("%." + decimalPoint + "f", value);
        this.measure = measure;
        this.decimalPoint = decimalPoint;
        this.valueWithMeasure = this.value + " " + measure;
    }

    @Override
    public String toString() {
        return "Value{value=" + valueWithMeasure + '}';
    }
}
