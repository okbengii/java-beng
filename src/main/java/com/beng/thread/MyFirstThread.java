package com.beng.thread;

/**
 * @desc 继承 Thread 实现多线程
 * @author apple
 */

public class MyFirstThread extends Thread {

    private int i;

    public void run() {
        for (; i < 100; i++) {
            /**
             * 当线程类继承 Thread 类时，直接使用 this 即可获得当前线程。 Thread 对象的 getName()
             * 返回当前线程的名字
             */
            System.out.println(getName() + " " + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            // 获取当前线程
            System.out.println(Thread.currentThread().getName() + " " + i);
            if (i == 20) {
                new MyFirstThread().start();
                new MyFirstThread().start();
            }
        }

    }
}
