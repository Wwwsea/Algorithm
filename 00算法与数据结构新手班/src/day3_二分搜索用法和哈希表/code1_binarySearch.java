package day3_二分搜索用法和哈希表;

import java.util.Arrays;

/**
 * @author:fish
 * @date: 2023/2/4-19:54
 * @content: 二分搜索
 */
public class code1_binarySearch {

    //arr保证有序
    public static boolean find(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return false;
        }
        int L = 0;
        int R = arr.length - 1;
        while (L <= R) {
            int mid = (L + R) / 2;
            if (arr[mid] == num)
                return true;
            else if (arr[mid] < num) {
                L = mid + 1;
            } else
                R = mid - 1;
        }
        return false;
    }

    //对数器部分
    public static int[] generateRandomArrary(int maxSize, int maxValue) {
        int N = (int) (Math.random() * maxSize);
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1)) - (int) (Math.random() * (maxValue));
        }
        return arr;
    }

    public static void sort(int[] arr) {
        int N = arr.length;
        for (int end = 1; end < N; end++) {
            for (int pre = end - 1; pre >= 0 && arr[pre] > arr[pre + 1]; pre--) {
                int temp = arr[pre];
                arr[pre] = arr[pre + 1];
                arr[pre + 1] = temp;
            }

        }
    }

    public static boolean test(int[] arr, int num) {
        for (int cur : arr) {
            if (cur == num)
                return true;
        }
        return false;
    }

    public static void main(String[] args) {
        int maxsize = 10;
        int mavValue = 100;
        int testTimes = 10000;
        boolean succeed = true;

        for (int i = 0; i < testTimes; i++) {
            int[] arr = generateRandomArrary(maxsize, mavValue);
//            sort(arr);
            Arrays.sort(arr);
            int value = (int) (Math.random() * (mavValue + 1)) - (int) (Math.random() * (mavValue));
            if (test(arr, value) != find(arr, value)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "NICE" : "fucking find!");
    }
}
