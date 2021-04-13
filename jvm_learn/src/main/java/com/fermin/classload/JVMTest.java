package com.fermin.classload;

/**
 * Klass 类
 * 分数组类和非数组类
 * InstanceKlass 包含子类 InstanceMirrorKlass， InstanceRefKlass， InstanceClassLoaderKlass
 * InstanceMirrorKlass 为 java class类的对象 ，在堆内存中存放
 * <p>
 * <p>
 * 类的加载的生命周期
 * loading -> verification -> preparation -> resolution -> initialization -> using -> unloading
 * <p>
 * 1. 加载
 * 通过类的全名获取存储该类的class文件
 */
public class JVMTest {

    private static final int d = 20;
    private static final String H = "world";
    private static int c;
    private static int b = 20;

    static {
        System.out.println("hello world");
    }

    public static void main(String[] args) {
        int[] testArray = new int[10];


        while (true) {
            //System.out.println("Hello World");
        }

    }

}
