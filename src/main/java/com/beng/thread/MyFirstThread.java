package com.beng.thread;

/*
 * @desc 继承 Thread 实现多线程
 *  线程的五个状态： 
 *      1. 创建  new Thread()
 *      2. 就绪  start()
 *      3. 运行  run()
 *      4. 阻塞 
 *          1) wait()
 *          2) 执行的方法被其它线程锁住
 *          3) sleep() join()
 *      5. 终止 执行完毕或者异常退出线程
 *  线程主要分为 Thread(继承) 和 Runnable(实现) 两类 （ 还有一种实现 Callable 接口，和 Future 结合 线程池使用 ）
 * 
 *  注意：乱序执行的代码，才有必要使用多线程（不要胡乱使用多线程）
 * @author apple
 */

public class MyFirstThread extends Thread {
    private String threadName;

    private int i;

    public MyFirstThread() {

    }

    public MyFirstThread(String threadName) {
        this.threadName = threadName;
    }

    @Override
    public void run() {
        for (; i < 100; i++) {
            /**
             * 当线程类继承 Thread 类时，直接使用 this 即可获得当前线程。 Thread 对象的 getName()
             * 返回当前线程的名字
             */
            System.out.println(this.threadName == null ? getName() : this.threadName + " " + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            // 获取当前线程
            System.out.println(Thread.currentThread().getName() + " " + i);
            if (i == 20) {
                new MyFirstThread("thread one").start(); // 并不是立即运行，而是到就绪状态
                new MyFirstThread("thread two").start();
            }
        }

    }
}
