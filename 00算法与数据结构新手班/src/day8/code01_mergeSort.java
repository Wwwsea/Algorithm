package day8;

import java.util.Arrays;
import java.util.function.Predicate;

/**
 * @author:fish
 * @date: 2023/2/13-12:17
 * @content: N/2 相当于N>>1; N*2+1 相当于（（N<<1）| 1）
 * 并且为了优先级的考虑 括号一定要注意 注意运算优先级 加减是优先于位运算的
 */
public class code01_mergeSort {
    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length == 0)
            return;

        process(arr, 0, arr.length - 1);
    }

    //递归实现arr[L...R]有序
    public static void process(int[] arr, int L, int R) {
        if (L == R)
            return;
        //mid=(R+L)/2
        int mid = L + ((R - L) >> 1);
        process(arr, L, mid);
        process(arr, mid + 1, R);
        merge(arr, L, mid, R);

    }

    public static void merge(int[] arr, int L, int mid, int R) {
        int[] ans = new int[R - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = mid + 1;
        //如果两指针都不越界。copy小的放到ans数组里，指针前移。
        while (p1 <= mid && p2 <= R) {
            ans[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        //若p1还没越界
        while (p1 <= mid) {
            ans[i++] = arr[p1++];
        }
        //若p2还没越界
        while (p2 <= R) {
            ans[i++] = arr[p2++];
        }
        for (int j = 0; j < ans.length; j++) {
            arr[L + j] = ans[j];
        }
    }

    // 非递归方法实现   (抠边界，防止溢出)
    public static void mergeSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int step = 1;
        int N = arr.length;
        while (step < N) {
            int L = 0;
            while (L < N) {
                int M = 0;
                if (N - L >= step) {
                    M = L + step - 1;
                } else {
                    M = N - 1;
                }
                if (M == N - 1) {
                    break;
                }
                int R = 0;
                if (N - 1 - M >= step) {
                    R = M + step;
                } else {
                    R = N - 1;
                }
                merge(arr, L, M, R);
                if (R == N - 1) {
                    break;
                } else {
                    L = R + 1;
                }
            }
            if (step > N / 2) {
                break;
            }
            step *= 2;
        }

    }


//	public static void mergeSort2(int[] arr) {
//		if (arr == null || arr.length < 2) {
//			return;
//		}
//		int N = arr.length;
//		int mergeSize = 1;
//		while (mergeSize < N) {
//			int L = 0;
//			while (L < N) {
//				if (mergeSize >= N - L) {
//					break;
//				}
//				int M = L + mergeSize - 1;
//				int R = M + Math.min(mergeSize, N - M - 1);
//				merge(arr, L, M, R);
//				L = R + 1;
//			}
//			if (mergeSize > N / 2) {
//				break;
//			}
//			mergeSize <<= 1;
//		}
//	}

    //for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int l = (int) (Math.random() * (maxSize + 1));
        int[] arr = new int[l];
        for (int i = 0; i < l; i++) {
            int value = (int) (Math.random() * (maxValue + 1)) - (int) (Math.random() * (maxValue));
            arr[i] = value;
        }
        return arr;
    }

    //for test
    public static int[] copyArray(int[] arr) {
        int[] ans = new int[arr.length];
//        int i=0;
//        for (int num: arr){
//            ans[i]=num;
//            i++;
//        }
        for (int i = 0; i < arr.length; i++) {
            ans[i] = arr[i];
        }
        return ans;
    }

    //for test
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }

    //for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
//        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
        if (arr1 == null ^ arr2 == null) {
            return false;
        }
        if (arr1 == null && arr2 == null)
            return true;
        if (arr1.length != arr2.length)
            return false;

        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i])
                return false;
        }
        return true;
    }

    //for test
    public static void main(String[] args) {
        int testTimes = 100000;
        int maxSize = 50;
        int maxValue = 40;
        System.out.println("开始测试");
        for (int i = 0; i < testTimes; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            mergeSort(arr1);
            Arrays.sort(arr2);
            if (!isEqual(arr1, arr2)) {
                System.out.println("有问题 ！！");
                printArray(arr1);
                System.out.println();
                printArray(arr2);
                break;
            }
        }
        System.out.println("测试结束");
    }

}
