package com.beng.thread.process;

import java.io.IOException;
import java.util.Scanner;

/*
 * @desc 创建进程
 * @author apple
 */
public class ProcessorTest {

    public static void main(String[] args) throws IOException {
        // runTime();
        processBuilder();
    }

    public static void runTime() throws IOException {
        String cmd = "ls";
        Process process = Runtime.getRuntime().exec(cmd);
        Scanner scanner = new Scanner(process.getInputStream());

        while (scanner.hasNextLine()) {
            System.out.println(scanner.nextLine());
        }
        scanner.close();
    }

    public static void processBuilder() throws IOException {
        ProcessBuilder pb = new ProcessBuilder("ls");
        Process process = pb.start();
        Scanner scanner = new Scanner(process.getInputStream());

        while (scanner.hasNextLine()) {
            System.out.println(scanner.nextLine());
        }
        scanner.close();
    }
}
