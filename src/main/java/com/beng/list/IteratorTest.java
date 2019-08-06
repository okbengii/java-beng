package com.beng.list;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

public class IteratorTest {

    public static void main(String[] args) {
        Collection<String> books = new HashSet<>();
        books.add("高性能MySQL");
        books.add("深入理解Java虚拟机");
        books.add("鸟哥的Linux私房菜");
        Iterator<String> it = books.iterator();

        while (it.hasNext()) {
            String bookName = it.next();
            System.out.println(bookName);
            if (bookName.contains("Java")) {
                books.remove(bookName);
            }
        }
    }
}
