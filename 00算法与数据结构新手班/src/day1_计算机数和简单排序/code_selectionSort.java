package day1_计算机数和简单排序;

/**
 * @author:fish
 * @date: 2023/2/3-20:43
 * @content: 选择排序(无稳定性) and 冒泡排序 and 插入排序（数据状况好） 都为n^2
 */
public class code_selectionSort {
    //选择排序
    public static void selectSort(int[] arr) {
        if (arr == null || arr.length < 2)
            return;
        int N = arr.length;
        for (int i = 0; i < N; i++) {
            int minValueIndex = i;
            for (int j = i + 1; j < N; j++) {
                minValueIndex = arr[j] < arr[minValueIndex] ? j : minValueIndex;
            }
            swap(arr, minValueIndex, i);

        }
    }

    //冒泡排序
    public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length < 2)
            return;
        int N = arr.length;
        //0~n-1,0~n-2,0~0,0~end
        for (int end = N - 1; end >= 0; end--) {
            //0 1   1 2   2 3   3 4    end-1 end
            for (int j = 1; j <= end; j++) {
                if (arr[j - 1] > arr[j]) {
                    swap(arr, j - 1, j);
                }
            }

        }
    }

    //插入排序
    public static void insertSort1(int[] arr) {
        if (arr == null || arr.length < 2)
            return;
        int N = arr.length;
        // 0~0 完成 ，0~1，0~2，0~3，，，0~end,0~N-1
        for (int end = 1; end < N; end++) {
            int curNumIndex = end;
            //左边数存在且较大 则交换，并向左移，直至左边不存在数。
            while (curNumIndex - 1 >= 0 && arr[curNumIndex - 1] > arr[curNumIndex]) {
                swap(arr, curNumIndex - 1, curNumIndex);
                curNumIndex--;
            }
        }
    }

    public static void insertSort2(int[] arr) {
        if (arr == null && arr.length < 2)
            return;
        int N = arr.length;
        for (int end = 1; end < N; end++) {
            for (int pre = end - 1; pre >= 0 && arr[pre] > arr[pre + 1]; pre--) {
                swap(arr, pre, pre + 1);
            }
        }
    }

    //打印数组
    public static void printArrary(int[] arr) {

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");

        }
    }

    //对数器方法
    public static int[] LenRandomValueRandom(int maxLen, int maxValue) {
        int len = (int) (Math.random() * maxLen);
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            int value = (int) (Math.random() * maxValue);
            arr[i] = value;
        }
        return arr;
    }

    //判断是否排序
    public static boolean isSorted(int[] arr) {
        if (arr == null || arr.length < 2) {
            return true;
        } else {
            int minValue = arr[0];
            for (int i = 1; i < arr.length; i++) {
                if (arr[i] < minValue)
                    return false;
            }
            return true;
        }

    }

    //交换数据
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {12, 32, 21, 14, 432, 2};
        selectSort(arr);
        printArrary(arr);

        System.out.println("#######对数器方法##########");
        int testTimes = 10000;
        for (int i = 0; i < testTimes; i++) {
            int[] ans = LenRandomValueRandom(100, 46);
            insertSort2(ans);
            if (!isSorted(ans)) {
                printArrary(ans);
                System.out.println("排序失败");
            }
        }
        System.out.println("测试完成！");

    }
}
