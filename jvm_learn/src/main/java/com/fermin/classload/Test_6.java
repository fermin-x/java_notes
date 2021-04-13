package com.fermin.classload;

/**
 * final 修饰的
 */
public class Test_6 {
    public static void main(String[] args) {
        System.out.println(Test_6_A.str);
    }
}

class Test_6_A {
    public static final String str = "A str";

    static {
        System.out.println("Test_6_A static block");
    }
}
