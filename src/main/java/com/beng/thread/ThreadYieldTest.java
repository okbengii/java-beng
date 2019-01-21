package com.beng.thread;

/*
 * @desc yield()
 *     暂停当前正在执行的线程，并执行其他线程
 * @author apple
 */
public class ThreadYieldTest {

    public static void main(String[] args) {

        System.out.println("Thread Main is Running...");
        ThreadYield t1 = new ThreadYield("Thread 1");
        ThreadYield t2 = new ThreadYield("Thread 2");
        t1.start();
        t2.start();
        System.out.println("Thread Main is Ending...");

    }
}

class ThreadYield extends Thread {

    private String threadName;

    public ThreadYield(String threadName) {
        this.threadName = threadName;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; ++i) {
            System.out.println(this.threadName == null ? getName() : this.threadName + " num  " + i);
            if (5 == i) {
                this.yield();
            }
        }
    }
}
