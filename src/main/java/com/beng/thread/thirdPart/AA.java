package com.beng.thread.thirdPart;

public class AA {

    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public static void main(String[] args) throws InterruptedException {

        AA a = new AA();
        System.out.println(a.getValue());
        new Thread(() -> System.out.println(a.getValue())).start();
        Thread.sleep(1);
        a.setValue(8);
        System.out.println(a.getValue());

    }

}
