package com.beng.classloader;

public class SubClass extends SuperClass {

    public static int num = 0;
    static {
        System.out.println("SubClass init");
    }

    public SubClass() {

    }
}
