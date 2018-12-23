package com.beng.design_model;

/**
 * 设计模式之单例模式，保证系统中的一个类只有一个实例供外界访问
 * 
 * @author apple
 */

// 懒汉式
public class SingletonClass {
    private static SingletonClass instance = null;

    public static SingletonClass getInstance() {
        if (instance == null) {
            synchronized (SingletonClass.class) {
                if (instance == null)
                    instance = new SingletonClass();
            }
        }
        return instance;
    }

    private SingletonClass() {
    }
}

// 饿汉式
class Singleton {
    // 在自己内部定义自己的一个
    // 实例，只供内部调用
    private static final Singleton instance = new Singleton();

    private Singleton() {
        // do something
    }

    // 这里提供了一个供外部访问本class的静态方法，可以直接访问
    public static Singleton getInstance() {
        return instance;
    }
}
