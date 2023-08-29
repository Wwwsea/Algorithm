package day3_二分搜索用法和哈希表;

import java.util.Arrays;

/**
 * @author:fish
 * @date: 2023/2/5-11:01
 * @content: arr有序，查询 >=value最左的位置
 */

public class code2_nearestIndex {
    public static int nearestIndex(int[] arr, int value) {
        if (arr == null || arr.length == 0)
            return -1;
        int N = arr.length;
        int L = 0;  // 左边界
        int R = N - 1; // 右边界
        int index = -1; // 大于等于value的位置。
        while (L <= R) {
            int mid = (L + R) / 2;
            if (arr[mid] >= value) {
                R = mid - 1;
                index = mid;
            } else
                L = mid + 1;
        }
        return index;
    }

    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int N = (int) (Math.random() * maxSize + 1);
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = (int) (Math.random() * maxValue + 1);
        }
        return arr;
    }

    public static int test(int[] arr, int value) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= value)
                return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        int maxSize = 1000;
        int maxValue = 150;
        int testTimes = 10000;
        int value;
        boolean succeed = true;

        for (int i = 0; i < testTimes; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            value = (int) (Math.random() * maxValue + 1);
            Arrays.sort(arr);
            if (test(arr, value) != nearestIndex(arr, value)) {
                succeed = false;
            }
        }
        System.out.println(succeed ? "Nice" : "Fucking find");
    }
}
