package com.fermin.classload;

/**
 * Test_4_A 不会初始化，只是当做类使用
 */
public class Test_4 {
    public static void main(String[] args) {
        Test_4_A[] arrs = new Test_4_A[1];

        System.out.println("end");
    }
}

class Test_4_A {
    static {
        System.out.println("Test_4_A static Block");
    }
}
