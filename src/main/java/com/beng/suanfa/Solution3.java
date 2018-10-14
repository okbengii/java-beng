package com.beng.suanfa;

import java.util.ArrayList;
import java.util.List;

/**
 * @author apple
 * @desc 求某数字的质数集合
 */
public class Solution3 {

    public static void main(String[] args) {
        System.out.println(me(3));
    }

    public static List<Long> me(int j) {
        List<Long> result = new ArrayList<>();
        long num = j;
        while (num != 1) {
            for (long i = 2; i <= num; i++) {
                if (num % i == 0) {
                    result.add(i);
                    num = num / i;
                    break;
                }
            }
        }
        return result;
    }
}
