package com.beng.set;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class LinkedHashSetTest {
    public static void main(String[] args) {
        Set<String> books = new HashSet<>();
        books.add("深入理解Java虚拟机");
        books.add("高性能MySQL");
        books.add("鸟哥的Linux私房菜");
        System.out.println(books);

        Set<String> books1 = new LinkedHashSet<>();
        books1.add("深入理解Java虚拟机");
        books1.add("高性能MySQL");
        books1.add("鸟哥的Linux私房菜");
        System.out.println(books1);

    }
}
