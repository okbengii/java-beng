package com.beng.file;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import com.opencsv.CSVParser;
import com.opencsv.CSVReader;

public class FileReader {

    private static final String CSVPATH = "/Users/apple/Desktop/monitor/example.csv";
    public static final char UNDERLINE = '_';

    public static void main(String[] args) {
        readCsv();
    }

    // 读取数据
    public static void readCsv() {
        File file = new File(CSVPATH);
        try (FileInputStream fileIn = new FileInputStream(file);
                DataInputStream dataIn = new DataInputStream(fileIn);
                InputStreamReader in = new InputStreamReader(dataIn, "UTF-8");) {

            CSVReader csvReader = new CSVReader(in, CSVParser.DEFAULT_SEPARATOR, CSVParser.DEFAULT_QUOTE_CHARACTER,
                    CSVParser.DEFAULT_ESCAPE_CHARACTER, 1);
            String[] strs;
            while ((strs = csvReader.readNext()) != null) {
                System.out.println(Arrays.toString(strs));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 下划线转驼峰
     * 
     * @param param
     * @return
     */
    public static String underlineToCamel(String param) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (c == UNDERLINE) {
                if (++i < len) {
                    sb.append(Character.toUpperCase(param.charAt(i)));
                }
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
