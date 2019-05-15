package com.beng.thread.current.lock;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @desc 可重入锁
 * @author apple
 */
public class ReentrantLockTest {

    private ArrayList<Integer> arrayList = new ArrayList<Integer>();
    Lock lock = new ReentrantLock(); // 注意这个地方

    public static void main(String[] args) {
        final ReentrantLockTest test = new ReentrantLockTest();

        new Thread() {
            public void run() {
                test.insert1(Thread.currentThread());
            };
        }.start();

        new Thread() {
            public void run() {
                test.insert1(Thread.currentThread());
            };
        }.start();
    }

    public void insert(Thread thread) {
        lock.lock();
        try {
            System.out.println(thread.getName() + "得到了锁");
            for (int i = 0; i < 5; i++) {
                arrayList.add(i);
            }
        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            System.out.println(thread.getName() + "释放了锁");
            lock.unlock();
        }
    }

    public void insert1(Thread thread) {
        if (lock.tryLock()) {
            try {
                System.out.println(thread.getName() + "得到了锁");
                for (int i = 0; i < 5; i++) {
                    arrayList.add(i);
                }
            } catch (Exception e) {
                // TODO: handle exception
            } finally {
                System.out.println(thread.getName() + "释放了锁");
                lock.unlock();
            }
        } else {
            System.out.println(thread.getName() + "获取锁失败");
        }
    }
}
