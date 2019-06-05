package com.beng.annotation;

/**
 * @author apple
 */
public class MyTest {

    @Testable
    public static void m1() {

    }

    @Testable
    public static void m2() {

    }

    @Testable
    public static void m3() {

    }

    @Testable
    public static void m4() {

    }

    @Testable
    public static void m5() {

    }

    @Testable
    public static void m6() {
        throw new RuntimeException("Boom");
    }

    @Testable
    public static void m7() {
        throw new RuntimeException("Crash");
    }

}
