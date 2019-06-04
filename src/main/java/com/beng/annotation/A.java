package com.beng.annotation;

public class A implements AA {
    @Override
    @MyAnnotationMethod
    public void say() {
        System.out.println("A");
    }

}
