package com.beng.suanfa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * @author apple
 */
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String n = in.nextLine();
            String m = in.nextLine();
            if (n.equals("") || m.equals(""))
                return;
            String[] list = m.split(" ");
            if (list.length == 0) {
                return;
            }
            if (Integer.parseInt(n) != list.length) {
                return;
            }
            if (Integer.parseInt(n) < 3) {
                return;
            }
            List<Integer> temp = new ArrayList<>();
            temp.add(Integer.parseInt(list[0]));
            temp.add(Integer.parseInt(list[1]));
            for (int i = 2; i < list.length; i++) {
                temp.add(Integer.parseInt(list[i]));
                Collections.sort(temp, new Comparator<Integer>() {
                    public int compare(Integer o1, Integer o2) {
                        return o1.compareTo(o2);
                    }
                });
                int result = 0;
                for (int j = 0; j < temp.size() - 1; j++) {
                    result = temp.get(j) + result;
                }
                if (result > temp.get(temp.size() - 1)) {
                    System.out.println(i + 1);
                    break;
                }
            }
        }
    }
}
