package com.fermin.classload;

/**
 * final修饰的常量在准备阶段就进行了赋值，
 * 对final修饰的变量在使用时，不会触发这个类的初始化
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
