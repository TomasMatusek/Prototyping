package com.matusek.basic;

public class C06_Label {

    public static void main(String[] args) {
        test1();
    }

    static void test1() {
        block: {
            System.out.println("Doing some business logic ...");
            if (true)
                break block;
            System.out.println("This is never executed ...");
        }
        System.out.println("Continues ...");
    }

    static void test2() {
        boolean someConditon = true;
        boolean anotherConditon = false;
        outterLoop: for(int i = 0; i < 10; i++) {
            while(true) {
                // some code
                if (someConditon) break outterLoop; // break the for-loop
                if (anotherConditon) break; // break the while-loop
                // another code
            }
            // more code
        }
    }
}