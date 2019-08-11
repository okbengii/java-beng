package com.beng.thread.threadpool;

import java.util.concurrent.Executor;
import java.util.concurrent.Semaphore;

import org.apache.http.annotation.ThreadSafe;

/**
 * 使用信号量做限流器
 * 
 * @author apple
 */
@ThreadSafe
public class BoundedExecutor {
    private final Executor exec;
    private final Semaphore semaphore;

    public BoundedExecutor(Executor exec, int bound) {
        this.exec = exec;
        this.semaphore = new Semaphore(bound);
    }

    public void submitTask(final Runnable command) throws InterruptedException {
        semaphore.acquire();
        try {
            exec.execute(() -> {
                try {
                    command.run();
                } finally {
                    semaphore.release();
                }
            });
        } catch (Exception e) {
            semaphore.release();
        }
    }
}
