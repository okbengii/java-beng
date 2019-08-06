package com.beng.set;

import java.util.HashSet;
import java.util.Set;

class A {
    @Override
    public boolean equals(Object obj) {
        return true;
    }
}

class B {
    @Override
    public int hashCode() {
        return 2;
    }
}

class C {
    @Override
    public int hashCode() {
        return 1;
    }

    @Override
    public boolean equals(Object obj) {
        return true;
    }
}

public class HashSetTest {

    public static void main(String[] args) {

        Set<Object> set = new HashSet<>();
        set.add(new A());
        set.add(new A());
        set.add(new B());
        set.add(new B());
        set.add(new C());
        set.add(new C());
        System.out.println(set);
    }

}
