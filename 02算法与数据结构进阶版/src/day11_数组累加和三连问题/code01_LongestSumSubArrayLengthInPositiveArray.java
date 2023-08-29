package day11_数组累加和三连问题;

/**
 * @author:fish
 * @date: 2023/5/4-10:59
 * @content: 数组 < 一连 >问题
 * 正数数组arr 找出子串（连续）和为sum的最大长度
 * 有单调性 -> 滑动窗口
 * i ... 从左往右尝试以i开头的 所有符合要求的解
 */
public class code01_LongestSumSubArrayLengthInPositiveArray {
    /***
     *
     * @param arr 目标数组 且都为正数
     * @param K 目标和
     * @return 返回符合要求的最大长度
     */
    public static int getMaxLength(int[] arr, int K) {
        if (arr == null || arr.length < 1 || K <= 0)
            return 0;
        int wl = 0;
        int wr = 0;
        int sum = arr[0]; // 该窗体内的总合
        int len = 0;  // 符合要求的最大长度
        while (wr < arr.length) {
            if (sum == K) {
                len = Math.max(len, wr - wl + 1);
                sum -= arr[wl++];
            } else if (sum < K) {
                wr++;
                if (wr == arr.length)
                    break;
                sum += arr[wr];
            } else { // sum>k
                sum -= arr[wl++];
            }
        }
        return len;
    }


    // for test
    public static int right(int[] arr, int K) {
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                if (valid(arr, i, j, K)) {
                    max = Math.max(max, j - i + 1);
                }
            }
        }
        return max;
    }

    // for test
    public static boolean valid(int[] arr, int L, int R, int K) {
        int sum = 0;
        for (int i = L; i <= R; i++) {
            sum += arr[i];
        }
        return sum == K;
    }

    // for test
    public static int[] generatePositiveArray(int size, int value) {
        int[] ans = new int[size];
        for (int i = 0; i != size; i++) {
            ans[i] = (int) (Math.random() * value) + 1;
        }
        return ans;
    }

    // for test
    public static void printArray(int[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int len = 50;
        int value = 100;
        int testTime = 500000;
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generatePositiveArray(len, value);
            int K = (int) (Math.random() * value) + 1;
            int ans1 = getMaxLength(arr, K);
            int ans2 = right(arr, K);
            if (ans1 != ans2) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println("K : " + K);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("test end");
    }
}
