package com.beng.thread.current.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/*
 * @desc 读写锁
 *      readLock()和writeLock()用来获取读锁和写锁
 * 
 * @author apple
 */
public class ReadWriteLockTest {

    private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

    public static void main(String[] args) {
        final ReadWriteLockTest test = new ReadWriteLockTest();

        new Thread() {
            public void run() {
                test.get(Thread.currentThread());
            };
        }.start();

        new Thread() {
            public void run() {
                test.get(Thread.currentThread());
            };
        }.start();

    }

    public void get(Thread thread) {
        // 这里调用的写锁，和使用synchronize关键字打印的结果一样
        // 如果使用 readLock() 就不一样了
        rwl.writeLock().lock();
        try {
            long start = System.currentTimeMillis();

            while (System.currentTimeMillis() - start <= 1) {
                System.out.println(thread.getName() + "正在进行读操作");
            }
            System.out.println(thread.getName() + "读操作完毕");
        } finally {
            rwl.writeLock().unlock();
        }
    }
}
