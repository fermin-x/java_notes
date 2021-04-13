package com.fermin.classload;

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
    public static int val2 = 1;

    Test_22_A() {
        val1++;
        val2++;
    }

    public static Test_22_A getInstance() {
        return instance;
    }
}
