package com.beng.seralizable;

import java.io.Serializable;

public class Teacher implements Serializable {

    String name;

    Person p;

    public Teacher(String name, Person p) {
        this.name = name;
        this.p = p;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person getP() {
        return p;
    }

    public void setP(Person p) {
        this.p = p;
    }

}
