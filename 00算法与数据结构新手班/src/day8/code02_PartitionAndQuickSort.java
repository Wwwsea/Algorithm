package day8;

/**
 * @author:fish
 * @date: 2023/2/14-11:12
 * @content: 快速排序 O(N*logN)
 *
 * swap(arr, index + +, + + sectionR);等效于--> swap(arr,index,sectionR+1);index++;sectionR++;
 * swap(arr,--moreR,index);等效于--> swap(arr,moreR-1,index);moreR--;
 */
public class code02_PartitionAndQuickSort {
    // 1).当前数<=p时，当前数与 小区间右边的下一个的值做交换，小区间右移，数组指针右移
    // 2).当前数>p时，数组指针右移； (注：p为数组最后一位的值)
    public static void splitNum1(int[] arr) {
        //区间右边界的下标
        int sectionR = -1;
        //数组下标
        int index = 0;
        int N = arr.length;
        while (index < N) {
            if (arr[index] <= arr[N - 1]) {
                swap(arr, index++, ++sectionR);
            } else {
                index++;
            }
        }
    }

    //1)当前数<p，当前数与 小于区间右边的下一值做交换，小于区间右移，数组指针右移；
    //2)当前数>p,当前数与 大于区间左边的值做交换，大于区间左移,数组指针不懂；
    //3)当前数=p，数组指针右移；
    public static void splitNum2(int[] arr) {
        int N = arr.length;
        int lessL = -1;
        int moreR = N - 1;
        int index = 0;
        while (index < moreR) {
            if (arr[index] < arr[N - 1]) {
                swap(arr, ++lessL, index++);
            } else if (arr[index] > arr[N - 1]) {
                swap(arr, --moreR, index);
            } else {
                index++;
            }
        }
        swap(arr, index, N - 1);
    }

    public static void swap(int[] arr, int a, int b) {
        int t = arr[ a];
        arr[a] = arr[b];
        arr[b] = t;
    }
    //arr[L...R]上，拿arr[R]做划分值
    //L...R  完成 < = >
    //返回等于区域的下标值
    public static int[] partition(int[] arr,int L,int R){
        int lessR=L-1;
        int moreL=R;
        int index=L;
        while (index<moreL){
            if (arr[index]<arr[R]){
                swap(arr,++lessR,index++);
            }else if (arr[index]>arr[R]){
                swap(arr,--moreL,index);
            }else {
                index++;
            }
        }
        swap(arr,index,R);
        return new int[] {lessR+1,moreL};
    }
    public static void quickSort(int[] arr){
        if (arr==null||arr.length<2){
            return;
        }
        process(arr,0,arr.length-1);
    }
    public static void process(int[] arr,int L,int R){
        if (L>=R){
            return;
        }
        //L>R 范围合法
        int[] equalE=partition(arr,L,R);
        process(arr,L,equalE[0]-1);
        process(arr,equalE[1]+1,R);
    }

    //for test
    public static void main(String[] args) {
        int[] arr = {1, 4, 6,3, 2, 5, 6, 3, 5, 8, 3, 5};
//        splitNum2(arr);
        quickSort(arr);
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}
