package com.beng.suanfa.sort;

import java.util.Arrays;

/**
 * @desc 选择排序
 * @author apple
 * @date 2019年10月20日
 */
public class SelectSort {

    public static void sort(int[] arr) {
        int temp = 0; // temp 保存最小的值
        int index = 0; // 保存 最小值 temp 的索引位置
        // 共有n轮循环
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            temp = arr[i]; // 保存当前的最小值
            index = i; // 记录最小值的位置
            // 开始遍历，寻找最小值
            // 从第 i+1 个开始
            for (int j = i + 1; j < n; j++) {
                // 如果有比 temp 小的值，就将当前值给到temp值，即当前的最小值
                if (temp > arr[j]) {
                    index = j;
                    temp = arr[j];
                }
            }
            // 一轮比较之后，如果最小值的索引不等于当前的索引值的话，说明要交换位置
            if (index != i) {
                // 交换位置 swap
                arr[index] = arr[i];
                arr[i] = temp;
            }
            System.out.println(Arrays.toString(arr));
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[] { 9, 1, 8, 3, 6, 2, 7 };
        sort(arr);
    }
}
