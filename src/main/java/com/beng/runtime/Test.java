package com.beng.runtime;

import java.io.InputStream;

import org.apache.commons.io.IOUtils;

/**
 * Java 执行 exec, 并读取 console 的结果
 * 
 * @author apple
 */
public class Test {

    public static void main(String[] args) {
        Runtime rt = Runtime.getRuntime();
        try {
            // 如果在系统中使用 Runtime 时，注意一定要对传入得命令进行校验
            Process prce = rt.exec("ifconfig");
            int result = prce.waitFor();
            if (result != 0) {
                System.out.println("process error: " + result);
            }
            InputStream is = result == 0 ? prce.getInputStream() : prce.getErrorStream();
            System.out.println(IOUtils.toString(is));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
