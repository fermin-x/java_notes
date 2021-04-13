package com.fermin.classload;

import java.util.UUID;

/**
 * UUID.randomUUID().toString(); 不是常量
 */
public class Test_7 {
    public static void main(String[] args) {
        System.out.println(Test_7_A.uuid);
    }
}

class Test_7_A {
    public static final String uuid = UUID.randomUUID().toString();

    static {
        System.out.println("Test_7_A static block");
    }
}
