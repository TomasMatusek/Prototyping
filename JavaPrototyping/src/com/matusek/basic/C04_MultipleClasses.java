package com.matusek.basic;

public class C04_MultipleClasses {
    private int value = 0; // Online init

    public C04_MultipleClasses(int value) {
        this.value = value; // Constructor init
    }

    public void Test() {
        // Not a constructor
    }
}

// Non public class can be in the same file
class Utils {

}
