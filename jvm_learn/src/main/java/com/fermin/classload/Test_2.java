package com.fermin.classload;

/**
 * 初始化⼀个类的⼦类会去加载其⽗类,并执行父类的初始化方法
 */
public class Test_2 {
    public static void main(String[] args) {
        System.out.println(Test_2_B.str);
    }
}

class Test_2_A {
    static {
        System.out.println("A static Block");
    }
}

class Test_2_B extends Test_2_A {
    public static String str = "B str";

    static {
        System.out.println("B static Block");
    }
}