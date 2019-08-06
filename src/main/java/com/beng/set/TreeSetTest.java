package com.beng.set;

import java.util.Comparator;
import java.util.TreeSet;

class M {

    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "M [age=" + age + "]";
    }

}

public class TreeSetTest {
    public static void main(String[] args) {

        TreeSet set = new TreeSet<>(new Comparator<M>() {
            @Override
            public int compare(M o1, M o2) {
                return o1.getAge() > o2.getAge() ? -1 : o1.getAge() < o2.getAge() ? 1 : 0;
            }
        });

        // TreeSet set = new TreeSet<>(
        // (o1, o2) -> ((M) o1).getAge() > ((M) o2).getAge() ? -1 : ((M)
        // o1).getAge() < ((M) o2).getAge() ? 1 : 0);
        M m1 = new M();
        m1.setAge(10);
        M m2 = new M();
        m2.setAge(20);
        set.add(m2);
        set.add(m1);
        System.out.println(set);
    }
}
