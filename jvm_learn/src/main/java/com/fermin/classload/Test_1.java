package com.fermin.classload;

/**
 * Test_1_B 会加载不会初始化
 * str 直接引用, 在Test_1_A中，不会触发Test_1_B的初始化
 * 调优指令 java -XX:+PrintFlagsFinal -version | grep Classloading
 */
public class Test_1 {
    public static void main(String[] args) {
        System.out.println(Test_1_B.str);
    }
}

class Test_1_A {
    public static String str = "A str";

    static {
        System.out.println("A static Block");
    }
}

class Test_1_B extends Test_1_A {
    static {
        System.out.println("B static Block");
    }
}
