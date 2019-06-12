package com.beng.thread.volatile_bak;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 可见性和原子性
 * 
 * @author apple
 */
public class VolatileTest {

    /**
     * done 这个类不能保证可见性，可能会读取到旧值
     * 
     * @author apple
     */
    static final class ControllerStop extends Thread {

        private boolean done = false;

        public void run() {
            while (!done) {
                try {
                    Thread.currentThread().sleep(2000);
                } catch (Exception e) {
                    Thread.currentThread().interrupt();
                }
            }
        }

        public void shutdown() {
            done = true;
        }

    }

    /**
     * 使用 volatile 保证可见性
     * 
     * @author apple
     */
    static final class ControllerStopV1 extends Thread {

        private volatile boolean done = false;

        public void run() {
            while (!done) {
                try {
                    Thread.currentThread().sleep(2000);
                } catch (Exception e) {
                    Thread.currentThread().interrupt();
                }
            }
        }

        public void shutdown() {
            done = true;
        }

    }

    /**
     * 使用原子类 AtomicBoolean 保证可见性
     * 
     * @author apple
     */
    static final class ControllerStopV2 extends Thread {

        private AtomicBoolean done = new AtomicBoolean(false);

        public void run() {
            while (!done.get()) {
                try {
                    Thread.currentThread().sleep(2000);
                } catch (Exception e) {
                    Thread.currentThread().interrupt();
                }
            }
        }

        public void shutdown() {
            done.set(true);
        }

    }

    /**
     * 使用原子类 AtomicBoolean 保证可见性
     * 
     * @author apple
     */
    static final class ControllerStopV3 extends Thread {

        private boolean done = false;

        public void run() {
            while (!done) {
                try {
                    Thread.currentThread().sleep(2000);
                } catch (Exception e) {
                    Thread.currentThread().interrupt();
                }
            }
        }

        public synchronized void shutdown() {
            done = true;
        }

    }

}
