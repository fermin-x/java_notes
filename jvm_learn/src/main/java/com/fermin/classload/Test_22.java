package com.fermin.classload;

/**
 * 准备阶段 val1=0, val2=0
 * 初始化阶段
 * 1） val1=0 ，val2=0
 * 2） 在new Test_22_A(); 时 val1=1 val2=1
 * 3） putstatic #3 <com/fermin/classload/Test_22_A.val2>
 */
public class Test_22 {
    public static void main(String[] args) {
        Test_22_A obj = Test_22_A.getInstance();

        System.out.println(Test_22_A.val1);
        System.out.println(Test_22_A.val2);
    }
}

class Test_22_A {
    public static int val1 = 0;
    public static Test_22_A instance = new Test_22_A();
    public static int val2 = 5;

    Test_22_A() {
        val1++;
        val2++;
    }

    public static Test_22_A getInstance() {
        return instance;
    }
}
