package com.matusek.basic.C12_OOP.composition;

public class IntelProcessor implements Processor {
    @Override
    public int calculate(int value) {
        return value * 1852;
    }
}
