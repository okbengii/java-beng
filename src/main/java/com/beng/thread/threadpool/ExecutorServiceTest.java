package com.beng.thread.threadpool;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/*
 * @desc 线程池学习
 *      Java 通过 Executors 提供四种线程池, 例如：
 *              1. newCachedThreadPool 创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
 *              2. newFixedThreadPool 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
 *              3. newScheduledThreadPool 创建一个定长线程池，支持定时及周期性任务执行。
 *              4. newSingleThreadExecutor 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。
 *              
 *      线程池接口 ExecutorService
 *          1. ExecutorService： 真正的线程池接口。
 *          2. ScheduledExecutorService： 能和Timer/TimerTask类似，解决那些需要任务重复执行的问题。
 *          3. ThreadPoolExecutor： ExecutorService的默认实现。
 *          4. ScheduledThreadPoolExecutor： 继承ThreadPoolExecutor的ScheduledExecutorService接口实现，周期性任务调度的类实现。
 * @author apple
 */
public class ExecutorServiceTest {
    public static void main(String[] args) {
        // Returns the number of processors available to the Java virtual
        // machine
        // int count = Runtime.getRuntime().availableProcessors();
        // System.out.println(count);

        // 1. 可缓存线程池
        // newCachedThreadPoolMe();
        // 2. 定长线程池
        // newFixedThreadPoolMe();
        // 3. 定长线程池，支持定时及周期性任务执行
        // newScheduleThreadPoolMe();
        // 4. 单线程化的线程池
        // newSingleThreadExecutorMe();
        test();
    }

    // newCachedThreadPool 可缓存线程池
    public static void newCachedThreadPoolMe() {
        ExecutorService pool = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; ++i) {
            final int index = i;
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(index);
                }
            });
        }
        pool.shutdown();
    }

    // newFixedThreadPool 定长线程池
    public static void newFixedThreadPoolMe() {
        ExecutorService pool = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 100; ++i) {
            final int index = i;
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(index);
                }
            });
        }
        pool.shutdown();
    }

    // newScheduleThreadPool 定长线程池，支持定时及周期性任务执行
    public static void newScheduleThreadPoolMe() {
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(5);
        for (int i = 0; i < 100; ++i) {
            final int index = i;
            pool.schedule(new Runnable() {
                @Override
                public void run() {
                    System.out.println(index);
                }
            }, 10, TimeUnit.SECONDS);
        }
        pool.shutdown();
    }

    // newSingleThreadExecutor 单线程化的线程池
    public static void newSingleThreadExecutorMe() {
        ExecutorService pool = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 100; ++i) {
            final int index = i;
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(index);
                }
            });
        }
        pool.shutdown();
    }

    /**
     * 
     */
    public static void test() {

        System.out.println(Runtime.getRuntime().availableProcessors());

        ExecutorService pool = Executors.newSingleThreadExecutor();
        pool.execute(new Runnable() {
            @Override
            public void run() {
                int i = 10 / 0;
            }
        });

        Future<Boolean> future = pool.submit(new Callable<Boolean>() {
            @Override
            public Boolean call() {
                int i = 10 / 0;
                return true;
            }
        });

        try {
            future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        pool.shutdown();
    }

}
