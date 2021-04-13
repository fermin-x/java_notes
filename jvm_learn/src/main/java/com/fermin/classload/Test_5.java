package com.fermin.classload;

/**
 *
 */
public class Test_5 {
    public static void main(String[] args) {
        Test_5_A obj = new Test_5_A();
        System.out.println("end");
    }
}

class Test_5_A {
    static {
        System.out.println("Test_5_A static Block");
    }
}
