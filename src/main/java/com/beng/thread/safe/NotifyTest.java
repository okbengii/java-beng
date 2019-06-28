package com.beng.thread.safe;

/**
 * 使用 notifyAll 不要使用 notify; 使用notify 会发生死锁
 * 
 * @author apple
 */
public class NotifyTest implements Runnable {

    private static int time = 0;

    private static final Object lock = new Object();

    private final int step;

    private NotifyTest(int step) {
        this.step = step;
    }

    @Override
    public void run() {

        try {
            synchronized (lock) {
                while (time != step) {
                    System.out.println(Thread.currentThread().getName() + " " + time);
                    lock.wait(10000);
                }
                System.out.println(Thread.currentThread().getName() + " " + time);
                time++;
                lock.notify();

                // lock.notifyAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupted();
        }
    }

    /**
     * 容易造成死锁
     * 
     * @param args
     */
    public static void main(String[] args) {

        for (int i = 0; i < 4; i++) {
            new Thread(new NotifyTest(i)).start();
        }

    }

}
