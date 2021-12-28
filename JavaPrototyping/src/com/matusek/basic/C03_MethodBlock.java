package com.matusek.basic;

public class C03_MethodBlock {
    public void run() {
        int a = 10;

        // Block, b exists only in this block
        {
            int b = 20;
            System.out.println(b);
        }

        // Cannot resolve symbol 'b'
        // System.out.println(b);
    }
}
