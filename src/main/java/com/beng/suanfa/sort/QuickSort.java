package com.beng.suanfa.sort;

import java.util.Arrays;

/**
 * @desc 快排
 * @author apple
 * @date 2019年10月22日
 */
public class QuickSort {

    /**
     * @desc
     * @param arr
     * @param low
     * @param high
     */
    public static void sort(int[] arr, int low, int high) {
        int left = low;
        int right = high;
        if (low > high) {
            return;
        }
        // 找基准点
        int index = arr[left];
        // 开始排序
        while (left < right) {
            // 从右向左-> 开始找比 index 大的值放在左边，小的值放在右边和 index 的位置进行交换
            while (left < right && arr[right] >= index) {
                right--;
            }
            // 找到值比 index 小的值都放在左边
            if (left < right) {
                arr[left++] = arr[right];
            }

            // 从左向右 <-
            while (left < right && arr[left] < index) {
                left++;
            }
            if (left < right) {
                arr[right--] = arr[left];
            }

        }
        System.out.println(Arrays.toString(arr));

        arr[left] = index;
        sort(arr, low, left - 1);
        sort(arr, left + 1, high);
    }

    public static void quickSort(int[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    public static void main(String[] args) {
        int[] arr = new int[] { 5, 4, 2, 9, 6, 1 };
        quickSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
