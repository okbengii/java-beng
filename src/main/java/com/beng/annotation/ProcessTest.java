package com.beng.annotation;

import java.lang.reflect.Method;

/**
 * @author apple
 */
public class ProcessTest {

    public static void process(String clazz) throws ClassNotFoundException {

        int passed = 0;
        int failed = 0;

        for (Method m : Class.forName(clazz).getMethods()) {
            if (m.isAnnotationPresent(Testable.class)) {
                try {
                    m.invoke(null);
                    passed++;
                } catch (Exception e) {
                    e.getStackTrace();
                    failed++;
                }
            }
        }
        System.out.println("共运行了: " + (passed + failed) + " 个方法，其中失败了: " + failed + "个，成功了: " + passed + "个。");
    }

    public static void main(String[] args) throws Exception {
        process("com.beng.annotation.MyTest");
    }
}
