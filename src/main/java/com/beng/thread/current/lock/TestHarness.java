package com.beng.thread.current.lock;

import java.util.concurrent.CountDownLatch;

public class TestHarness {

    public static void main(String[] args) throws InterruptedException {
        timeTasks(5, new Runnable() {
            @Override
            public void run() {
                System.out.println("11111111");
            }
        });
    }

    public static long timeTasks(int nThreads, final Runnable task) throws InterruptedException {
        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(nThreads);
        for (int i = 0; i < nThreads; i++) {
            System.out.println("开始生成任务...");
            Thread t = new Thread() {
                public void run() {
                    try {
                        startGate.await();
                        try {
                            task.run();
                        } finally {
                            endGate.countDown();
                        }
                    } catch (InterruptedException e) {

                    }
                }
            };
            t.start();
        }
        long start = System.currentTimeMillis();
        System.out.println("先执行主线程");
        startGate.countDown(); // 启动门
        System.out.println("开始执行子线程");
        endGate.await(); // 结束门
        System.out.println("子线程执行完毕");
        long end = System.currentTimeMillis();
        return nThreads;
    }

}
