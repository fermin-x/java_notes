package com.fermin.classload;

/**
 *
 */
public class Test_9 {
    public static void main(String[] args) {
        System.out.println(Test_9_B.str);
    }
}

class Test_9_A {
    public static String str = "A str";

    static {
        System.out.println("A static Block");
    }
}

class Test_9_B extends Test_9_A {
    static {
        str += "#11";
        System.out.println("B static Block");
    }
}
