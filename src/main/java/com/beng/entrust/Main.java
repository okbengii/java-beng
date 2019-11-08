package com.beng.entrust;

public class Main {

    public static void main(String[] args) {
        LazyCustom lazy = new LazyCustom();
        CodeDelegation entrust = new CodeDelegation();
        entrust.addListener(lazy, "buyPencile", "2B");
        entrust.invokeEvent();
    }

}
