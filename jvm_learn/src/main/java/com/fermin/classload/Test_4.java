package com.fermin.classload;

/**
 * 数组在JVM中是动态数据类型， 数组的申明只是当做类型去使用，
 * Test_4_A[]的申明 不会导致Test_4_A初始化，只是当做类使用
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
