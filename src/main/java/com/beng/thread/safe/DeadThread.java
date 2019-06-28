package com.beng.thread.safe;

/**
 * 死锁程序
 * 
 * @author apple
 */
public class DeadThread implements Runnable {

    public String username;
    public Object lock1 = new Object();
    public Object lock2 = new Object();

    @Override
    public void run() {
        if (username.equals("a")) {
            synchronized (lock1) {
                try {
                    System.out.println("username = " + username);
                    System.out.println(Thread.currentThread().getName());
                    // 处理逻辑
                    Thread.sleep(3000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("lock1-> 尝试拿 lock2");
                synchronized (lock2) {
                    System.out.println("按lock1->lock2的顺序执行代码");
                }
            }
        }
        if (username.equals("b")) {
            synchronized (lock2) {
                try {
                    System.out.println("username = " + username);
                    System.out.println(Thread.currentThread().getName());
                    Thread.sleep(3000);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("lock2-> 尝试拿 lock1");
                synchronized (lock1) {
                    System.out.println("按lock2->lock1顺序执行代码");
                }
            }

        }
    }

    public void setFlag(String username) {
        this.username = username;
    }

    /**
     * 先执行线程 Thread-0，拿到锁 lock1，睡眠 3 秒; 主线程睡眠 2秒，开始运行线程 Thread-1，拿到锁 lock2;
     * Thread-0 尝试拿到锁 lock2, 但是 lock2 被 Thread—1 拿到, Thread-0 等待 lock2 锁释放; 3
     * 秒后, Thread-1 尝试拿到锁 lock1, 但是 Thread-0 持有锁 lock1, 并等待 lock2 释放, 形成死锁。
     * 
     * @param args
     */
    public static void main(String[] args) {

        DeadThread dt1 = new DeadThread();
        dt1.setFlag("a");
        Thread t1 = new Thread(dt1);
        t1.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        dt1.setFlag("b");
        Thread t2 = new Thread(dt1);

        t2.start();
    }
}