package com.beng.thread.threadpool;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池-饱和策略 调用者运行策略 CallerRunsPolicy
 * 
 * @author apple
 */
public class PolicyTest {

    private static ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 1, TimeUnit.MINUTES,
            new LinkedBlockingQueue<>(100));
    static {
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
    }

    public static void main(String[] args) {
        ServerSocket server = null;
        Socket socket = null;

        try {
            server = new ServerSocket(8080);
            while (true) {
                socket = server.accept();
                executor.execute(new SockerHandler(socket));
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static class SockerHandler implements Runnable {

        private Socket socket;

        public SockerHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                System.out.println(socket.getInputStream().toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
