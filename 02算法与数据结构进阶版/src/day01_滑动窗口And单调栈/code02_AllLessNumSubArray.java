package day01_滑动窗口And单调栈;

import java.util.LinkedList;

/**
 * @author:fish
 * @date: 2023/4/8-17:03
 * @content: 滑动窗口的使用
 *
 * 给定整形有序数组arr 和整形数字num
 * arr的子数组sub 想达标必须满足:
 * sub的最大值-sub的最小值 <= num
 * 返回达标个数
 */
public class code02_AllLessNumSubArray {
    public static int getNum(int[] arr, int num) {
        if (arr == null || arr.length == 0 || num < 1)
            return 0;
        LinkedList<Integer> qMin = new LinkedList<Integer>();
        LinkedList<Integer> qMax = new LinkedList<Integer>();
        int L = 0; //数组范围[L...R-1] 即[L...R) ,L比R小时窗口有数
        int R = 0;  //R最后一个达标位置的下一个位置
        int res = 0;
        for (L = 0; L < arr.length; L++) { //L为开头位置，尝试每个开头
            //进来的比队列中最后一个小,小的双端队列弹出再比较
            for (R = L; R < arr.length; R++) {
                while (!qMin.isEmpty() && arr[R] <= arr[qMin.peekLast()]) {
                    qMin.pollLast();
                }
                qMin.addLast(R);
                while (!qMax.isEmpty() && arr[R] >= arr[qMax.peekLast()]) {
                    qMax.pollLast();
                }
                qMax.addLast(R);

                if (arr[qMax.getFirst()] - arr[qMin.getFirst()] > num)
                    break;
            }
            res += R - L;

            //L即将过期
            if (qMin.pollFirst() == L)
                qMin.pollFirst();
            if (qMax.peekFirst() == L)
                qMax.pollFirst();
        }
        return res;
    }

    public static int getNumTry(int[] arr, int num) {
        if (arr.length == 0 || arr == null || num < 1)
            return 0;
        int res = 0;
        int L = 0;
        int R = 0;
        LinkedList<Integer> pMin = new LinkedList<Integer>();
        LinkedList<Integer> pMax = new LinkedList<Integer>();

        while (L < arr.length) {

            for (R = L; R < arr.length; R++) {
                while (!pMin.isEmpty() && arr[R] <= arr[pMin.peekLast()]) {
                    pMin.pollLast();
                }
                pMin.addLast(R);

                while (!pMax.isEmpty() && arr[R] >= arr[pMax.peekLast()]) {
                    pMax.pollLast();
                }
                pMax.addLast(R);

                if (arr[pMax.getFirst()] - arr[pMin.getFirst()] > num)
                    break;

            }
            res += R - L;

            if (pMin.peekFirst() == L)
                pMin.pollFirst();
            if (pMax.peekFirst() == L)
                pMax.pollFirst();
            L++;
        }

        return res;
    }


    //for test
    public static int[] generateArray(int maxSize, int maxValue) {
        int N = (int) Math.random() * maxSize + 1;
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = (int) Math.random() * maxValue + 1;
        }
        return arr;
    }
    //for test
    public static void printArray(int[] arr){
        for (int i : arr) {
            System.out.print(i+" ");
        }
    }

    //for test
    public static void main(String[] args) {
        int maxSize = 24;
        int maxValue = 25;
        int[] arr = generateArray(maxSize, maxValue);
        int a = getNum(arr, 5);
        int b = getNumTry(arr, 5);
        printArray(arr);
        System.out.println(a+"==="+b);
    }
}
