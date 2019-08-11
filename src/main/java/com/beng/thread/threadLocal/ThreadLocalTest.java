package com.beng.thread.threadLocal;

/*
 * ThreadLocal 每个线程的一个副本，互相不受影响 
 * 
 * InheritableThreadLocal 可以允许线程及该线程创建的子线程均可以访问同一个变量
 * 
 * @author apple
 */
public class ThreadLocalTest {

    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();
    // private static InheritableThreadLocal<Integer> threadLocal = new
    // InheritableThreadLocal<Integer>();

    private static class Task implements Runnable {
        private ThreadLocal<String> threadLocal = new ThreadLocal<>();

        private String taskName;

        public Task(String taskName) {
            this.taskName = taskName;
        }

        @Override
        public void run() {
            threadLocal.set(Thread.currentThread().getName());
            System.out.println(this.taskName + ":" + threadLocal.get());
        }
    }

    private static class Task1 implements Runnable {
        private ThreadLocal<String> threadLocal = new ThreadLocal<>();

        private String taskName;

        public Task1(String taskName) {
            threadLocal.set(Thread.currentThread().getName());
            this.taskName = taskName;
            System.out.println(Thread.currentThread().getName() + ":" + threadLocal.get());
        }

        @Override
        public void run() {
            System.out.println(this.taskName + ":" + threadLocal.get());
        }
    }

    private static class Task2 implements Runnable {

        private String taskName;

        public Task2(String taskName) {
            this.taskName = taskName;
            System.out.println(Thread.currentThread().getName() + ":" + threadLocal.get());
        }

        @Override
        public void run() {
            System.out.println(this.taskName + ":" + threadLocal.get());
        }
    }

    public static void main(String[] args) {
        Task task1 = new Task("任务1");
        Thread t1 = new Thread(task1);

        Task task2 = new Task("任务2");
        Thread t2 = new Thread(task2);

        t1.start();
        t2.start(); // 任务1:Thread-0
        // 任务2:Thread-1

        // Task1 的构造函数由 main 线程调用，所以 threadLocal.set() 是在 main 线程中完成的，但是在 子线程
        // run() 方法中没有调用 threadLocal.set() 所以 其 get() 到的值为 null
        // Task1 task1 = new Task1("任务1");
        // Thread t1 = new Thread(task1);
        //
        // Task1 task2 = new Task1("任务2");
        // Thread t2 = new Thread(task2);
        //
        // t1.start();
        // t2.start(); // main:main
        // main:main
        // 任务1:null
        // 任务2:null

        // threadLocal.set(10);
        // Task2 task1 = new Task2("任务1");
        // Thread t1 = new Thread(task1);
        //
        // Task2 task2 = new Task2("任务2");
        // Thread t2 = new Thread(task2);
        //
        // t1.start();
        // t2.start();
    }

}
