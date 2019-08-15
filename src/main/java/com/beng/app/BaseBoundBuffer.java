package com.beng.app;

import java.util.Arrays;

/**
 * 有界缓存
 * 
 * @author apple
 */
public class BaseBoundBuffer {

    public String[] buf;
    private int tail;
    private int count;
    private int head;

    public BaseBoundBuffer(int cap) {
        this.buf = new String[cap];
    }

    public synchronized void doPut(String v) {
        buf[tail] = v;
        if (++tail == buf.length)
            tail = 0;
        if (count < buf.length)
            count++;
        System.out.println("doPut count: " + count);
    }

    public synchronized String doTake() {
        String v = buf[head];
        buf[head] = null;
        if (++head == buf.length)
            head = 0;
        if (count > 0)
            --count;
        System.out.println("doTake count: " + count);
        return v;
    }

    public synchronized boolean isEmpty() {
        return count == 0;
    }

    public synchronized boolean isFull() {
        return count == buf.length;
    }

    public static void main(String[] args) {
        BaseBoundBuffer bbb = new BaseBoundBuffer(4);
        String[] arr = new String[] { "a", "b", "c", "d", "f" };
        for (String a : arr) {
            bbb.doPut(a);
        }
        System.out.println(Arrays.toString(bbb.buf));

        System.out.println(bbb.isFull());

        for (int i = 0; i < 5; i++) {
            System.out.println(bbb.doTake());
        }
        System.out.println(Arrays.toString(bbb.buf));

        System.out.println(bbb.isEmpty());

    }
}
