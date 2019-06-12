package com.beng.enums;

public class Main {
    public static void main(String[] args) {
        // 遍历
        Constant[] constants = Constant.values();
        for (Constant con : constants) {
            System.out.println(con);
        }
    }
}
