package com.beng.basic.integer;

public class Test {

    public static void main(String[] args) {
        System.out.println(Math.abs((Integer.MIN_VALUE)));
        Integer i = 126;
        Integer i1 = 126;

        Integer i3 = 129;
        Integer i4 = 129;
        System.out.println(i == i1);
        System.out.println(i3.intValue() == i4.intValue());

    }

}
