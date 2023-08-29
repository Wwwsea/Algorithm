package day01_滑动窗口And单调栈;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author:fish
 * @date: 2023/7/30-16:06
 * @content: 给定数组arr，求子数组中 累加和*最小值结果最大的值
 */
public class test {
    public static int getMax(int[] arr) {
        int[] preSum = new int[arr.length];
        Stack<List<Integer>> stack = new Stack<>();
        int[] rightLess = new int[arr.length];
        int res = Integer.MIN_VALUE;

        preSum[0] = arr[0]; // 生成前缀和预数组
        for (int i = 1; i < arr.length; i++) {
            preSum[i] = preSum[i - 1] + arr[i];
        }
        for (int i = 0; i < arr.length; i++) {
            // 从当前i位置扩到最右小的前一个，保证范围中最小值i位置并且范围最大
            while (!stack.isEmpty() && arr[i] < stack.peek().get(0)) {
                List<Integer> list = stack.pop();
                for (Integer popi : list) {
                    rightLess[popi] = i;
                }
            }
            if (!stack.isEmpty() && arr[i] == stack.peek().get(0)) {
                stack.peek().add(Integer.valueOf(i));
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(i);
                stack.add(list);
            }
        }
        while (!stack.isEmpty()) {
            List<Integer> list = stack.pop();
            for (Integer popi : list) {
                rightLess[popi] = -1;
            }
        }

        for (int i = 0; i < arr.length; i++) {
            Integer ri = rightLess[i];
            if (rightLess[i] != -1) {
                int right = Integer.parseInt(ri.toString());
                res = Math.max(res, i != 0 ? ((preSum[right - 1] - preSum[i - 1]) * arr[i]) : preSum[right - 1] * arr[i]);

            }
        }

        return res;
    }

    public static int max2(int[] arr) {
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
        int[] arr = new int[(int) (Math.random() * 10) + 5];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 10);
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
                System.out.println(getMax(arr) +" "+ maxBao(arr));
                System.out.println("FUCK!");
                break;
            }
        }
        System.out.println("test finish");
    }
}
