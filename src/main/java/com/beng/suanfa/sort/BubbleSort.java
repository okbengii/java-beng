package com.beng.suanfa.sort;

import java.util.Arrays;

/**
 * @desc 冒泡排序
 * @author apple
 * @date 2019年10月16日
 */
public class BubbleSort {

    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            boolean success = true;
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    success = false;
                }
            }
            if (success)
                break;
            System.out.println("第" + (i + 1) + "轮后: " + Arrays.toString(arr));

        }
    }

    public static void main(String[] args) {
        int[] arr = new int[] { 1, 6, 3, 5, 7, 9, 8, 4 };
        bubbleSort(arr);
    }

}
