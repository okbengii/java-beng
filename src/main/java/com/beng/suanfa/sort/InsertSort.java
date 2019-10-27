package com.beng.suanfa.sort;

import java.util.Arrays;

/**
 * @desc 插入排序
 * @author apple
 * @date 2019年10月20日
 */
public class InsertSort {

    public static void sort(int[] arr) {
        int n = arr.length;
        // 从 1 开始，把 arr[0] 当成一个序列
        for (int i = 1; i < n; i++) {
            int temp = arr[i];
            int j = i;
            // 开始判断 index = j-1 的元素是否需要添加到前边的序列当中
            if (arr[j - 1] > temp) {
                // 交换位置（注意条件）
                while (j >= 1 && arr[j - 1] > temp) {
                    arr[j] = arr[j - 1];
                    j--;
                }
                // 直到 arr[j-1] <=temp 的时候停止
                if (j != i) {
                    arr[j] = temp;
                }
            }
            System.out.println(Arrays.toString(arr));
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[] { 1, 9, 8, 3, 6, 2, 7 };
        sort(arr);
    }
}
