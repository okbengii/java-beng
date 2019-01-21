package com.beng.thread;

/**
 * @desc Runnable 接口
 * @author apple
 */
class MyFirstRunnableTest implements Runnable {

    private String theadName;

    private MyFirstRunnableTest() {
    }

    public MyFirstRunnableTest(String theadName) {
        this.theadName = theadName;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; ++i) {
            if (5 == i) {
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(this.theadName + " is Running NUM: " + i);
        }
    }
}

public class MyFirstRunnable {
    public static void main(String[] args) {
        MyFirstRunnableTest test = new MyFirstRunnableTest("Thread 1"); // Runnable
                                                                        // 接口实现的线程
                                                                        // 也是需要
                                                                        // Thread
                                                                        // 类的
                                                                        // start()
                                                                        // 方法进行调用的
        Thread t = new Thread(test);
        t.setPriority(Thread.MIN_PRIORITY);
        t.start();
        MyFirstRunnableTest test1 = new MyFirstRunnableTest("Thread 2");
        Thread t1 = new Thread(test1);
        t1.setPriority(Thread.MAX_PRIORITY);
        t1.start();

        // new Thread() {
        // public void run() {
        // System.out.println("111111111");
        // }
        // }.start();
    }
}
