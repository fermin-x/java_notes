package com.fermin.classload;

/**
 * 准备阶段 val1 val2 赋初值 val1=0 val2=0
 * 初始化阶段 val1=0 val2=1
 */
public class Test_21 {
    public static void main(String[] args) {
        Test_21_A obj = Test_21_A.getInstance();

        System.out.println(Test_21_A.val1);
        System.out.println(Test_21_A.val2);
    }
}

class Test_21_A {
    public static int val1 = 0;
    public static int val2 = 1;

    public static Test_21_A instance = new Test_21_A();

    Test_21_A() {
        val1++;
        val2++;
    }

    public static Test_21_A getInstance() {
        return instance;
    }
}
