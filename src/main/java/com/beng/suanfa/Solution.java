package com.beng.suanfa;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author apple
 */
public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            String input = in.nextLine();
            if (input.equals(""))
                return;
            String[] mn = input.split("\\|");
            if (mn.length != 2)
                return;
            String m = mn[0];
            int n = Integer.parseInt(mn[1]);
            Map<Integer, Integer> map = new HashMap<>();
            String[] list = m.split(",");
            int count = 0;

            for (int i = 0; i < list.length; i++) {
                map.put(Integer.parseInt(list[i]), i + 1);
            }
            for (int i = 0; i < list.length; i++) {
                int temp = n - Integer.parseInt(list[i]);
                if (map.containsKey(temp)) {
                    count++;
                    map.remove(Integer.parseInt(list[i]));
                }
            }
            System.out.println(count);
        }

    }
}
