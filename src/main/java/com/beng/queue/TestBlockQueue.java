package com.beng.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * {@link} https://www.cnblogs.com/lemon-flm/p/7877898.html
 * 
 * @desc 测试一下阻塞队列
 * @author apple
 */
// 五个队列所提供的各有不同：
// * ArrayBlockingQueue ：一个由数组支持的有界队列。
// * LinkedBlockingQueue ：一个由链接节点支持的可选有界队列。
// * PriorityBlockingQueue ：一个由优先级堆支持的无界优先级队列。
// * DelayQueue ：一个由优先级堆支持的、基于时间的调度队列。
// * SynchronousQueue ：一个利用 BlockingQueue 接口的简单聚集（rendezvous）机制。

// add 增加一个元索 如果队列已满，则抛出一个IIIegaISlabEepeplian异常
// remove 移除并返回队列头部的元素 如果队列为空，则抛出一个NoSuchElementException异常
// element 返回队列头部的元素 如果队列为空，则抛出一个NoSuchElementException异常
// offer 添加一个元素并返回true 如果队列已满，则返回false
// poll 移除并返问队列头部的元素 如果队列为空，则返回null
// peek 返回队列头部的元素 如果队列为空，则返回null
// put 添加一个元素 如果队列满，则阻塞
// take 移除并返回队列头部的元素 如果队列为空，则阻塞
public class TestBlockQueue {

    private static class Basket {
        // 定义篮子的容量
        BlockingQueue<String> basket = new ArrayBlockingQueue<>(3);

        // 生产苹果，放入篮子
        public void produce() throws InterruptedException {
            // put方法放入一个苹果，若basket满了，等到basket有位置
            // put 添加一个元素 如果队列满，则阻塞
            basket.put("An apple");
        }

        // 消费苹果，从篮子中取走
        public String consume() throws InterruptedException {
            // get方法取出一个苹果，若basket为空，等到basket有苹果为止
            // take 移除并返回队列头部的元素 如果队列为空，则阻塞
            String apple = basket.take();
            return apple;
        }

        public int getAppleNumber() {
            return basket.size();
        }
    }

    // 测试方法
    public static void testBasket() {
        // 建立一个装苹果的篮子
        final Basket basket = new Basket();
        // 定义苹果生产者
        class Producer implements Runnable {
            public void run() {
                try {
                    while (true) {
                        // 生产苹果
                        System.out.println("生产者准备生产苹果：" + System.currentTimeMillis());
                        basket.produce();
                        System.out.println("生产者生产苹果完毕：" + System.currentTimeMillis());
                        System.out.println("生产完后有苹果：" + basket.getAppleNumber() + "个");
                        // 休眠300ms
                        Thread.sleep(300);
                    }
                } catch (InterruptedException ex) {
                }
            }
        }
        // 定义苹果消费者
        class Consumer implements Runnable {
            public void run() {
                try {
                    while (true) {
                        // 消费苹果
                        System.out.println("消费者准备消费苹果：" + System.currentTimeMillis());
                        basket.consume();
                        System.out.println("消费者消费苹果完毕：" + System.currentTimeMillis());
                        System.out.println("消费完后有苹果：" + basket.getAppleNumber() + "个");
                        // 休眠1000ms
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException ex) {
                }
            }
        }

        ExecutorService service = Executors.newCachedThreadPool();
        Producer producer = new Producer();
        Consumer consumer = new Consumer();
        service.submit(producer);
        service.submit(consumer);
        // 程序运行10s后，所有任务停止
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
        }
        service.shutdownNow();
    }

    public static void main(String[] args) {
        TestBlockQueue.testBasket();
    }

}
