package day3_二分搜索用法和哈希表;

/**
 * @author:fish
 * @date: 2023/2/5-12:03
 * @content: 二分法
 * 数组无序且相邻两个数不相等，求局部最小数，不唯一
 * boolean leftBigger = left >= 0 ? arr[left] > arr[index] : true;//若是越界，不破环规则,直接返回true；若没越界再去比较
 */
public class code3_localMin {
    public static int oneMinIndex(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        if (arr.length == 1) {
            return 0;
        }
        //最左为小值
        if (arr[0] < arr[1]) {
            return 0;
        }
        //最右为小值
        if (arr[arr.length - 1] < arr[arr.length - 2]) {
            return arr.length - 1;
        }

        int left = 0;
        int right = arr.length - 1;

        while (left < right - 1) {//保证有三个数在比较
            int mid = (left + right) / 2;
            //4种情况 ① 左>我 我<右  (符合)
            //2,左>我 我>右 （砍大）
            //3,左<我 我<右
            //4,左<我 我>右
            if (arr[mid] < arr[mid - 1] && arr[mid] < arr[mid + 1]) {
                return mid;
            } else {
                if (arr[mid - 1] < arr[mid])
                    right = mid - 1;
                else
                    left = mid + 1;
            }
        }
        return arr[left] < arr[right] ? left : right;

    }

    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int N = (int) (Math.random() * maxSize + 1);
        int[] arr = new int[N];
        if (N > 0) {
            arr[0] = (int) (Math.random() * maxValue + 1);
            for (int i = 1; i < N; i++) {
                do {
                    arr[i] = (int) (Math.random() * maxValue + 1);
                } while (arr[i] == arr[i - 1]);
            }
        }
        return arr;
    }

    public static boolean check(int[] arr, int index) {
        if (arr.length == 0)
            return index == -1;
        int left = index - 1;
        int right = index + 1;
        boolean leftBigger = left >= 0 ? arr[left] > arr[index] : true;//若是越界，不破环规则；若没越界再去比较
        boolean rightBigger = right < arr.length ? arr[right] > arr[index] : true;
        return leftBigger && rightBigger;
    }

    public static void print(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int maxSize = 5;
        int maxValue = 20;
        int testTimes = 10000;
        System.out.println("begin");
        for (int i = 0; i < testTimes; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            int oneMinIndex = oneMinIndex(arr);
            if (!check(arr, oneMinIndex)) {
                print(arr);
                System.out.println("下标值为：" + oneMinIndex);
                break;
            }
        }
        System.out.println("end");
    }

}
