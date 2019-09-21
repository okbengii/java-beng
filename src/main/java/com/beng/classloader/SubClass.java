package com.beng.classloader;

public class SubClass extends SuperClass {

    static {
        System.out.println("SubClass init");
    }
}
