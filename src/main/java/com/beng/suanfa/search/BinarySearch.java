package com.beng.suanfa.search;

/**
 * @desc 二分查找
 * @author apple
 * @date 2019年10月15日
 */
public class BinarySearch {

    /**
     * @desc 二分查找，将索引返回
     * @param k
     * @param arr
     * @return
     */
    public static int search(int k, int[] arr) {
        int low = 0;
        int high = arr.length - 1; // 注意一定是 arr.length - 1,
                                   // 如果high=arr.length，可能数组越界
        while (low <= high) { // 终止条件是 low <= high
            // high-low >> 1 计算中间的距离
            // low + 中间的距离，即中间位置
            int mid = low + ((high - low) >> 1);
            if (arr[mid] == k)
                return mid;
            if (arr[mid] < k)
                low = mid + 1;
            if (arr[mid] > k)
                high = mid - 1;
        }
        return -1;
    }

    /**
     * @desc 找到最后一次出现的位置
     * @param k
     * @param arr
     * @return
     */
    public static int last(int k, int[] arr) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            // 注意这个限制条件
            int mid = low + ((high - low + 1) >> 1);
            // 当 k >= arr[mid], 说明 k 还需要向右边走 low = mid
            if (arr[mid] <= k) {
                low = mid;
            } else {
                high = mid - 1;
            }
            // 当 low=high 的时候，说明已经查找完毕，直接跳出
            if (low == high) {
                break;
            }
        }
        if (arr[high] == k)
            return high;
        return -1;
    }

    /**
     * @desc 找到第一次出现 k 的位置
     * @param k
     * @param arr
     * @return
     */
    public static int first(int k, int[] arr) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            // 当 k <= arr[mid], 说明 k 还需要向左边走 high = mid
            if (k <= arr[mid]) {
                high = mid;
            } else {
                low = mid + 1;
            }
            // 当 low=high 的时候，说明已经查找完毕，直接跳出
            if (low == high) {
                break;
            }
        }
        if (arr[high] == k)
            return high;
        return -1;
    }

    /**
     * @desc 查找第一个小于的
     * @param k
     * @param arr
     * @return
     */
    public static int firstLowerIndex(int k, int[] arr) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            // 当 k <= arr[mid], 说明 k 还需要向左边走 high = mid
            if (k <= arr[mid]) {
                high = mid;
            } else {
                low = mid + 1;
            }
            // 当 low=high 的时候，说明已经查找完毕，直接跳出
            if (low == high) {
                break;
            }
        }
        if (arr[high] == k)
            return high - 1;
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = new int[] { 1, 3, 5, 8, 11, 16 };
        System.out.println(search(11, arr));
        int[] arr1 = new int[] { 1, 3, 5, 5, 5, 8, 11, 16 };
        System.out.println(first(5, arr1));
        System.out.println(last(5, arr1));
        System.out.println(firstLowerIndex(5, arr1));

    }
}
