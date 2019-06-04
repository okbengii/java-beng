package com.beng.thread.volatile_bak;

import java.util.concurrent.CountDownLatch;

/**
 * @author apple
 */
public class Test {

    //  测试一下 volatile
    private static int num;
    private static CountDownLatch latch = new CountDownLatch(2);

    public static void main(String[] args) {
        new Thread() {
            public void run() {
                for (int i = 0; i < 100000; ++i) {
                    num = num + 1;
                    System.out.println("current num:" + num);
                }
                latch.countDown();
            }
        }.start();
        new Thread() {
            public void run() {
                for (int i = 0; i < 100000; ++i) {
                    System.out.println("current num:" + num);
                }
                latch.countDown();
            }
        }.start();
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("final num:" + num);
    }
}
