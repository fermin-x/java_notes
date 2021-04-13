package com.fermin.classload;

public class Test_8 {
    public static void main(String[] args) throws ClassNotFoundException {
        Class clazz = Class.forName("com.fermin.classload.Test_8_A");

        System.out.println("end");
    }
}

class Test_8_A {
    static {
        System.out.println("Test_8_A static block");
    }
}
