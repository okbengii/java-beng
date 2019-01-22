package com.beng.seralizable;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Java 对象序列化
 * 
 * @author apple
 */
public class SeralizableTest {

    public static void main(String[] args) {

        // 输出
        try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("src/main/resources/object.txt"))) {
            Person p = new Person("Jack", "18");
            os.writeObject(p);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 读取
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/main/resources/object.txt"))) {
            Person p1 = (Person) ois.readObject();
            System.out.println(p1.getName());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        /*
         * 引用对象序列化 如果 Teacher 对象没有实现 seralizable 接口
         * 报错：java.io.NotSerializableException
         */
        try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("src/main/resources/object1.txt"))) {
            Person p = new Person("Jack", "18");
            Teacher t1 = new Teacher("Tom", p);
            Teacher t2 = new Teacher("Janny", p);
            os.writeObject(p);
            os.writeObject(t1);
            os.writeObject(t2);
            os.writeObject(p);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
