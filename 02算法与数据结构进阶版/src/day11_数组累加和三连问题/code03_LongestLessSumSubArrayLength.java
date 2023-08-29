package day11_数组累加和三连问题;

import java.util.PriorityQueue;
import java.util.Stack;

/**
 * @author:fish
 * @date: 2023/5/4-11:53
 * @content: 数组 <三连> 问题
 * 数组arr（包含正数，负数，零 ） 找出子串（连续）和<=sum的最大长度
 * 难点: 可能性舍弃  找是否可往右扩，更新最大范围 忽略子范围
 * 时间复杂度 O(N)
 */
public class code03_LongestLessSumSubArrayLength {
    public static int getMaxLengthAwesome(int[] arr, int K) {
        if (arr == null || arr.length < 1)
            return 0;
        int N = arr.length;
        int[] minSum = new int[N];
        int[] minSumEnd = new int[N];
        minSum[N - 1] = arr[N - 1];
        minSumEnd[N - 1] = N - 1;
        for (int i = N - 2; i >= 0; i--) {
            if (minSum[i + 1] <= 0) {
                minSum[i] = minSum[i + 1] + arr[i];
                minSumEnd[i] = minSumEnd[i + 1];
            } else {
                minSum[i] = arr[i];
                minSumEnd[i] = i;
            }
        }
        // i是窗口的最左的位置，end扩出来的最右有效块儿的最后一个位置的，再下一个位置  (i...)(...)(...)(end...)
        // end也是下一块儿的开始位置
        // i....(sum)
        // 窗口：[i~end)
        // res 为符合要求的最大长度
        int end = 0;
        int sum = 0;
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            // while循环结束之后：
            // 1) 如果以i开头的情况下，累加和<=k的最长子数组是arr[i..end-1]，看看这个子数组长度能不能更新res；
            // 2) 如果以i开头的情况下，累加和<=k的最长子数组比arr[i..end-1]短，更新还是不更新res都不会影响最终结果；
            while (end < N && sum + minSum[end] <= K) { // 若不越界 且当前sum加上下一个end开头的块 小于等于目标值，则继续往外扩
                sum += minSum[end];
                end = minSumEnd[end] + 1;
            }
            res = Math.max(res, end - i);

            if (end > i) {  // 若窗口内还有数
                sum -= arr[i];
            } else {  // 窗口内已经没有数了，说明从i开头的所有子数组累加和都不可能<=k
                end = i + 1;
            }
        }
        return res;
    }

    public static int getMaxLength(int[] arr, int K) {
        if (arr == null || arr.length < 1)
            return 0;

        // 数组h记录符合要求的范围 （i...h[i]）
        int[] h = new int[arr.length + 1];
        // sum i...(sum和)
        int sum = 0;
        h[0] = sum;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            h[i + 1] = Math.max(sum, h[i]);
        }
        sum = 0;
        int pre = 0;
        int len = 0;
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            pre = getLessIndex(h, sum - K);
            len = pre == -1 ? 0 : i - pre + 1;
            res = Math.max(res, len);
        }
        return res;
    }

    public static int getLessIndex(int[] arr, int num) {
        int low = 0;
        int high = arr.length - 1;
        int mid = 0;
        int res = -1;
        while (low <= high) {
            mid = (low + high) / 2;
            if (arr[mid] >= num) {
                res = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return res;
    }

    // for test
    public static int[] generateRandomArray(int len, int maxValue) {
        int[] res = new int[len];
        for (int i = 0; i != res.length; i++) {
            res[i] = (int) (Math.random() * maxValue) - (maxValue / 3);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println("test begin");
        for (int i = 0; i < 10000000; i++) {
            int[] arr = generateRandomArray(10, 20);
            int k = (int) (Math.random() * 20) - 5;
            if (getMaxLengthAwesome(arr, k) != getMaxLength(arr, k)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish");

    }

}
