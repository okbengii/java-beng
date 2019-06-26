package com.beng.rpc.avro;

import java.io.File;
import java.io.IOException;

import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;

public class Main {

    public static void main(String[] args) throws IOException {

        // 初始化对象
        // 1. get/set
        User user = new User();
        user.setName("Janny");
        user.setFavoriteColor("red");
        user.setFavoriteNumber(110);

        // 2. 构造函数
        User user1 = new User("Danny", 119, "black");
        System.out.println(user1.toString());

        // 3. Builder 模式
        User user2 = User.newBuilder().setName("LiMing").setFavoriteColor("yellow").setFavoriteNumber(120).build();

        // 将文件序列化到 user.avro
        String path = "user.avro";
        DatumWriter<User> userDatumWriter = new SpecificDatumWriter<>(User.class);
        DataFileWriter<User> dataFileWriter = new DataFileWriter<>(userDatumWriter);
        dataFileWriter.create(user1.getSchema(), new File(path));
        dataFileWriter.append(user1);
        dataFileWriter.append(user2);
        dataFileWriter.append(user);
        dataFileWriter.close();
        System.out.println();
        // 读取序列化的文件
        DatumReader<User> reader = new SpecificDatumReader<>(User.class);
        DataFileReader<User> dataFileReader = new DataFileReader<>(new File("user.avro"), reader);
        User user4 = null;
        while (dataFileReader.hasNext()) {
            user4 = dataFileReader.next();
            System.out.println(user4);
        }
    }
}
