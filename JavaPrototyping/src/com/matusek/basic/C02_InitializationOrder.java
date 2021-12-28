package com.matusek.basic;

public class C02_InitializationOrder {
    public static void main(String[] args) {
        Test test = new Test("Hello");
        System.out.println(test.getStringValue());
        System.out.println(Test.staticValue);
    }
}

class Test {

    static int staticValue;
    private String stringValue = "Default";

    // Static block - init of static values
    static {
        int otherValue = 523; // This exists only in scope of this block
        staticValue = 10;
        System.out.println("Block executed");
    }

    Test(String stringValue) {
        this.stringValue = stringValue;
        System.out.println("Constructor executed");

    }

    public String getStringValue() {
        return stringValue;
    }
}
