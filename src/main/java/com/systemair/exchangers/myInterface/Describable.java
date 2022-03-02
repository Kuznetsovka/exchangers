package com.systemair.exchangers.myInterface;

public interface Describable {
    default String getModelSystemair(){
        return "";
    }
    String getTxt();
}
