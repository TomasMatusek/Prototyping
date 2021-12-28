package com.matusek.basic.C12_OOP.composition;

public class Computer {

    private Processor processor;
    private Memory memory;

    // Dependency injection OR aggregation ??? maybe both unclear
    public Computer(Processor processor, Memory memory) {
        this.processor = processor;
        this.memory = memory;
    }

    // Composition exclusive ownership
    public Computer() {
        this.processor = new IntelProcessor();
        this.memory = new DDR4Memory();
    }

    public int calculateNumber(int value) {
        return processor.calculate(value);
    }

    public void storeValue(String value, String key) {
        memory.store(key, value);
    }

    public String getValue(String key) {
        return memory.get(key);
    }
}
