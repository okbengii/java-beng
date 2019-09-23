package com.beng.date.java8;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * description: java8 时间工具类
 * 
 * @date 2019年9月23日
 */
public class DateUtils {

    private DateUtils() {

    }

    /**
     * description: 获取当前日期
     * 
     * @return 2019-09-23
     */
    public static String getNowDate() {
        LocalDate localDate = LocalDate.now();
        return localDate.toString();
    }

    /**
     * description: 获取 pattern 格式的当前日期
     * 
     * @param pattern
     *            日期格式
     * @return
     */
    public static String getNowDateWithPattern(String pattern) {
        LocalDate localDate = LocalDate.now();
        return localDate.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * description: 将字符串转化为 LocalDate 格式
     * 
     * @param date
     *            日期字符串
     * @param pattern
     *            日期格式
     * @return
     */
    public static LocalDate formatToDate(String date, String pattern) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * description: 获取昨天的日期
     * 
     * @return
     */
    public static String getLastDay() {
        LocalDate localDate = LocalDate.now();
        LocalDate lastDay = localDate.minusDays(1);
        return lastDay.toString();
    }

    /**
     * description: 获取当前时间
     * 
     * @return 22:00:39.310
     */
    public static String getNowTime() {
        return LocalTime.now().toString();
    }

    public static String getNowTime(String pattern) {
        return LocalTime.now().format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * description: 获取当前日期时间
     * 
     * @return
     */
    public static String getNowDateTime() {
        LocalDateTime localDateTime = LocalDateTime.now();
        return localDateTime.toString();
    }

    /**
     * description: 获取当前日期时间
     * 
     * @param pattern
     * @return
     */
    public static String getNowDateTime(String pattern) {
        LocalDateTime localDateTime = LocalDateTime.now();
        return localDateTime.format(DateTimeFormatter.ofPattern(pattern == null ? "yyyy-MM-dd HH:mm:ss" : pattern));
    }

    public static void main(String[] args) {
        System.out.println(getNowDate());
        System.out.println(getNowDateWithPattern("yyyyMMdd"));
        System.out.println(formatToDate("20190923", "yyyyMMdd"));
        System.out.println(getLastDay());
        System.out.println(getNowTime());
        System.out.println(getNowTime("HH:mm:ss"));
        System.out.println(getNowDateTime());
        System.out.println(getNowDateTime(null));

    }
}
