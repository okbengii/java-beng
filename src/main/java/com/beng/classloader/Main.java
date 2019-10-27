package com.beng.classloader;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException {

        for (int i = 0; i < 100; i++) {
            Class.forName("com.beng.classloader.SubClass");
            System.out.println(SubClass.num);

        }
        // System.out.println(SubClass.num);
    }
}
