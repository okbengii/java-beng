package com.beng.jvm;

/**
 * 测试 object 相互引用 gc
 * 
 * @author apple
 */
public class ReferenceCountGc {

    public Object instance = null;
    private static final int _1MB = 1024 * 1024;

    /**
     * 占内存用，在 GC 日志中看是否被回收
     */
    private byte[] bigSize = new byte[2 * _1MB];

    public static void testGC() {

        ReferenceCountGc objA = new ReferenceCountGc();
        ReferenceCountGc objB = new ReferenceCountGc();
        objA.instance = objB;
        objB.instance = objA;
        objA = null;
        objB = null;

        System.gc();
    }

}
