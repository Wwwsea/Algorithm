package day01_滑动窗口And单调栈;

import java.util.Stack;

/**
 * @author:fish
 * @date: 2023/4/10-20:42
 * @content: 正数数组arr。arr中任意一个子数组sub，都可算出(sub累加和)*(sub最小值)
 * 返回子数组中最大值
 * <p>
 * 思路: 1.生成前缀和  2~5位置上的和表示为preSum[5]-preSum[1];
 */
public class code04_AllTimesMinToMax {

    public static int getMax(int[] arr) {
        int size = arr.length;
        int[] sums = new int[size];
        sums[0] = arr[0];

        for (int i = 1; i < size; i++) {
            sums[i] = sums[i - 1] + arr[i];
        }
        int max = Integer.MIN_VALUE;

        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0; i < size; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                int j = stack.pop();
                max = Math.max(max, (stack.isEmpty() ? sums[i - 1] : (sums[i - 1] - sums[stack.peek()])) * arr[j]);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int j = stack.pop();
            max = Math.max(max, (stack.isEmpty() ? sums[size - 1] : (sums[size - 1] - sums[stack.peek()])) * arr[j]);
        }
        return max;
    }


    public static int maxBao(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                int minNum = Integer.MAX_VALUE;
                int sum = 0;
                for (int k = i; k <= j; k++) {
                    sum += arr[k];
                    minNum = Math.min(minNum, arr[k]);
                }
                max = Math.max(max, minNum * sum);
            }
        }
        return max;
    }


    //for test
    public static int[] gerenareRondomArray() {
        int[] arr = new int[(int) (Math.random() * 20) + 10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 101);
        }
        return arr;
    }

    public static void main(String[] args) {
        int testTimes = 2000000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            int[] arr = gerenareRondomArray();
//            for (int i1 : arr) {
//                System.out.print(i1+" ");
//            }
//            if (getMax(arr) != maxBao(arr)) {
            if (getMax(arr) != maxBao(arr)) {
                System.out.println("FUCK!");
                break;
            }
        }
        System.out.println("test finish");
    }
}
