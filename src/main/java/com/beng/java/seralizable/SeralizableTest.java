package com.beng.java.seralizable;

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
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("object.txt"))) {
            Person p1 = (Person) ois.readObject();
            System.out.println(p1.getName());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
