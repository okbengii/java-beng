package com.beng.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 时间学习
 * 
 * @author apple
 */
public final class DateUtils {

    /**
     * 返回当前时间
     * 
     * @return Mon Nov 26 18:30:38 CST 2018
     */
    public static Date getNowDate() {
        return new Date();
    }

    /**
     * 返回当前时间的 13 位时间戳
     * 
     * @return 1543228325716
     */
    public static long getNowTime() {
        return System.currentTimeMillis();
    }

    /**
     * Date 转 字符串
     * 
     * @param date
     * @return 2018-11-26 06:34:58
     */
    public static String formatTime(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return sdf.format(date);
    }

    /**
     * 时间转为 date 类型
     * 
     * @param date
     * @return
     */
    public static Date transferStringToDate(String date, String pattern) {

        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date result = null;
        try {
            result = sdf.parse(date);
        } catch (ParseException e) {
            e.getMessage();
        }
        return result;

    }

    /**
     * Calendar类 获取昨天的日期时间
     * 
     * @return
     */
    public static Date getLastDay(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_MONTH, -1);
        return c.getTime();
    }

    /**
     * 获取对应的时间
     * 
     * @param date
     * @return
     */
    public static Map<String, Integer> getTime(Date date) {
        Map<String, Integer> map = new HashMap<>();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int year = c.get(Calendar.YEAR);
        map.put("year", year);
        int month = c.get(Calendar.MONTH) + 1; // 获取月份需要 +1
        map.put("month", month);
        int day = c.get(Calendar.DAY_OF_MONTH);
        map.put("day", day);
        return map;
    }

    /**
     * 时间戳转日期类型
     * 
     * @param st
     * @return
     */
    public static Date transformLongToDate(long st) {
        Date date = new Date(st);
        return date;
    }

    public static void main(String[] args) {
        print(getNowDate());
        print(getNowTime());
        print(formatTime(new Date()));
        print(transferStringToDate("2018-10-25 11:25:35", "yyyy-MM-dd hh:mm:ss"));
        print(getLastDay(transferStringToDate("2018-10-25 11:25:35", "yyyy-MM-dd hh:mm:ss")));
        print(getTime(transferStringToDate("2018-10-25 11:25:35", "yyyy-MM-dd hh:mm:ss")));
        print(transformLongToDate(1500214131321L));

    }

    public static void print(Object t) {
        System.out.println(t.toString());
    }
}
