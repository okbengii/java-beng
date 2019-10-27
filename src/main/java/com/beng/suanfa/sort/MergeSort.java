package com.beng.suanfa.sort;

import java.util.Arrays;

/**
 * @desc 归并排序
 * @author apple
 * @date 2019年10月23日
 */
public class MergeSort {

    public static void sort(int[] arr, int low, int high) {
        if (low >= high) {
            return;
        }
        int mid = (low + high) / 2;
        // 左边一半
        sort(arr, low, mid);
        // 右边一半
        sort(arr, mid + 1, high);
        // 合并
        merge(arr, low, mid, high);
    }

    public static void merge(int[] arr, int low, int mid, int high) {
        int[] temp = new int[high - low + 1];// 定义一个 temp 数组
        int i = 0;
        int p1 = low;
        int p2 = mid + 1;
        // 比较左右两部分的元素，哪个小，把那个元素填入temp中
        while (p1 <= mid && p2 <= high) {
            temp[i++] = arr[p1] > arr[p2] ? arr[p2++] : arr[p1++];
        }
        // 上面的循环退出后，把剩余的元素依次填入到temp中
        // 以下两个while只有一个会执行
        while (p1 <= mid) {
            temp[i++] = arr[p1++];
        }
        while (p2 <= high) {
            temp[i++] = arr[p2++];
        }
        // 把最终的排序的结果复制给原数组
        for (i = 0; i < temp.length; i++) {
            arr[low + i] = temp[i];
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[] { 9, 2, 4, 1, 5, 3, 8 };
        sort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }
}
