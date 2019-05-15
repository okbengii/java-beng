package com.beng.code.standard;

/**
 * 类的循环初始化
 * 
 * @author apple
 */
public class Cycle {

    private int balance;
    private static final Cycle c = new Cycle();
    private static final int deposite = (int) (Math.random() * 100);

    public Cycle() {
        balance = deposite - 10;
    }

    // 在类 c 进行初始化时，deposite 还有没有初始化，导致 deposite = 0；所以会出现 -10 的情况
    // 所以，在有多个字段为 static 时，应该将其放到初始化对象的前面
    public static void main(String[] args) {
        System.out.println("The account balance: " + c.balance);
    }
}
