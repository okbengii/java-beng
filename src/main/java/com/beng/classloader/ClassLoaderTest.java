package com.beng.classloader;

import java.io.InputStream;

public class ClassLoaderTest {

    public static void main(String[] args)
            throws InstantiationException, IllegalAccessException, ClassNotFoundException {

        ClassLoader classLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try {
                    String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                    InputStream is = getClass().getResourceAsStream(fileName);
                    if (is == null) {
                        return super.loadClass(name);
                    }
                    byte[] b = new byte[is.available()];
                    is.read(b);
                    return defineClass(name, b, 0, b.length);
                } catch (Exception e) {
                    throw new ClassNotFoundException(name);
                }
            }
        };

        Object obj = classLoader.loadClass("com.beng.classloader.SubClass").newInstance();
        System.out.println(obj.getClass());
        System.out.println(obj instanceof SubClass);
    }
}
