package com.beng.thread;

/*
 * @desc wait() notify() synchronized() 
 *      针对于 object 对象 
 *      
 *      synchronized
 *          1. 作用域
 *              1) 对象实例
 *              2) 类方法
 * @author apple
 */
public class ThreadSyncTest {

    public static void main(String[] args) throws InterruptedException {
        Object a = new Object();
        Object b = new Object();
        Object c = new Object();
        ThreadSync t1 = new ThreadSync("A", c, a);
        ThreadSync t2 = new ThreadSync("B", a, b);
        ThreadSync t3 = new ThreadSync("C", b, c);

        Thread thead1 = new Thread(t1);
        Thread thead2 = new Thread(t2);
        Thread thead3 = new Thread(t3);
        thead1.start();
        Thread.sleep(1000L);
        thead2.start();
        Thread.sleep(1000L);

        thead3.start();

    }

}

class ThreadSync implements Runnable {

    private String threadName;
    private Object pre;
    private Object current;
    private int count = 10;

    public ThreadSync(String threadName, Object pre, Object current) {
        this.threadName = threadName;
        this.pre = pre;
        this.current = current;
    }

    @Override
    public void run() {

        while (count > 0) {
            synchronized (pre) {
                synchronized (current) {
                    System.out.println("Current Word: " + this.threadName);
                    count--;
                    current.notify();
                }
                try {
                    pre.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
