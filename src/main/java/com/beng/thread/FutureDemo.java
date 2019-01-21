package com.beng.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @title 线程池学习
 * @author apple
 */
public class FutureDemo {
   private static ExecutorService executorService = Executors.newFixedThreadPool(3);

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // executorService.execute(new RunnableTask()); // 直接执行
        // Future future = executorService.submit(new RunnableTask());
        // mainWork();
        // System.out.println(future.get());
        String[] threads = { "thread1", "thread2", "thread3" };
        List<Future> futures = new ArrayList<>();
        for (String thread : threads) {
            Task task = new Task(thread);
            Future f = executorService.submit(task);
            // System.out.println(f.get()); // 这里 f.get() 会阻塞线程
            futures.add(f);
        }
        mainWork();
        for (Future<String> future : futures) {
            System.out.println(future.get());
        }
        executorService.shutdown();
    }

    // 主线程工作
    private static void mainWork() {
        System.out.println("Main thread start work!");
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Main Thread work done!");
    }

    // 有返回值
    public static class Task implements Callable<String> {
        private String name;

        public Task(String name) {
            this.name = name;
        }

        @Override
        public String call() throws Exception {
            System.out.println("Thread " + this.name + " is starting...");
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread " + this.name + " is ending...");
            return name;
        }
    }
}
