package com.beng.thread.safe;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用 condition
 * 
 * @author apple
 */
// 因为 构造方法抛出异常，所以要定义成 final 类
public final class ConditionTest implements Runnable {

    private static final Lock lock = new ReentrantLock();
    private static final Condition condition = lock.newCondition();

    private static int time = 0;
    private static final int MAX_STEPS = 5;
    private static Condition[] conditions = new Condition[MAX_STEPS];
    private final int step;

    public ConditionTest(int step) {
        this.step = step;
        if (step < MAX_STEPS) {
            conditions[step] = lock.newCondition();
        } else {
            throw new IllegalArgumentException("Too many threads .");
        }
    }

    @Override
    public void run() {
        lock.lock();
        try {
            while (time != step) {
                System.out.println(Thread.currentThread().getName() + " " + step + "  wait " + time);
                // condition.await();
                conditions[step].await();
            }
            System.out.println(Thread.currentThread().getName() + " " + step + " " + time);
            time++;
            // condition.signal();
            // condition.signalAll();
            if (step + 1 < conditions.length) {
                conditions[step + 1].signal();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupted();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        for (int i = 4; i >= 0; i--) {
            new Thread(new ConditionTest(i)).start();
        }
    }

}
