package com.beng.innerclass;

import java.util.UUID;

/**
 * @desc 内部类学习
 * @where 哪里可以用到？
 * @one 在你的一个方法需要传大量的参数的时候，尽量不要大量的传参，这样不易读，容易造成传错误都不易察觉，最好是将通用的方法的参数抽象出一个对象，采用以下的方式，构建对象，这样不仅易读，并且易于维护
 * @two 在设置对象属性的时候，不在需要set方法了，只需要一条 xxx.xxx.xxx.xxx.xxx.xxx就可以了。
 * @author apple
 */
public class Student {
    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Student(Builder builder) {
        setId(builder.id);
        setName(builder.name);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private String id;
        private String name;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Student build() {
            return new Student(this);
        }
    }

    public String toString() {
        return "[ " + "id: " + this.id + " , name: " + this.name + " ]";
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; ++i) {
            Student stu = Student.newBuilder().id(UUID.randomUUID().toString()).name(Integer.toString(i)).build();
            System.out.println(stu.toString());
        }
    }
}
