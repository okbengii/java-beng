package com.beng.thread;

/*
 * @desc join() 方法 
 *      当前线程调用其它线程的 join() 方法，当前线程进入阻塞状态，等待其它线程执行完毕 
 * @author apple
 */
public class ThreadJoinTest {

    public static void main(String[] args) {

        System.out.println("Thread Main is Running ....");
        ThreadJoin t1 = new ThreadJoin("Thread 1");
        ThreadJoin t2 = new ThreadJoin("Thread 2");
        t1.start();
        t2.start();
        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Thread Main is Ending ....");
    }

}

class ThreadJoin extends Thread {

    private Thread thread;

    private String threadName;

    private ThreadJoin() {

    }

    public ThreadJoin(String threadName) {
        this.threadName = threadName;
    }

    public ThreadJoin(String threadName, Thread thread) {
        this.threadName = threadName;
        this.thread = thread;
    }

    @Override
    public void run() {
        if (this.thread != null) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < 10; ++i) {
            System.out.println(this.threadName == null ? getName() : this.threadName + " num  " + i);
        }
    }

}
