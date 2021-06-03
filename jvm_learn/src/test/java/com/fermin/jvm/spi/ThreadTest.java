package com.fermin.jvm.spi;

public class ThreadTest {

    public static void main(String[] args) throws InterruptedException {
        Thread t = new MyThread();
        t.start();
        Thread.sleep(1000);

        /*
         * 下面两个执行的结果不一样
         */
//        t.interrupt(); //
//        t.join(); // 等待t线程结束

//        t.join(); // 等待t线程结束
        t.interrupt(); //
        System.out.println("end");
    }

    static class MyThread extends Thread {
        public void run() {

            int n = 0;
//            synchronized (this) {
            while (!Thread.interrupted()) {
                //while (!isInterrupted()) {

//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                n++;
                System.out.println(n + " hello!");
//                }
            }
        }
    }
}
