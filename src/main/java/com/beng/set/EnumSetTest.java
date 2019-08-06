package com.beng.set;

import java.util.EnumSet;

enum Season {
    SPRING, SUMMER, FALL, WINTER;
}

public class EnumSetTest {

    public static void main(String[] args) {
        EnumSet set = EnumSet.allOf(Season.class);
        System.out.println(set);
        EnumSet set1 = EnumSet.noneOf(Season.class);
        System.out.println(set1);
        set1.add(Season.SPRING);
        set1.add(Season.SUMMER);
        System.out.println(set1);

    }
}
