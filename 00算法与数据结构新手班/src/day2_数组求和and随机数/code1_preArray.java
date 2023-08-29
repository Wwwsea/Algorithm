package day2_数组求和and随机数;

/**
 * @author:fish
 * @date: 2023/2/3-11:38
 * @content:
 * 前缀和数组：适用于原数组较大，
 * 矩阵求和：查询次数多，占用空间大
 */
public class code1_preArray {
    //矩阵求和
    public static class RangeSum1 {
        int[] arr;

        public RangeSum1(int[] arr) {
            this.arr = arr;
        }

        public int rangeSum(int L, int R) {
            int sum = 0;
            for (int i = L; i <= R; i++) {
                sum += arr[i];
            }
            return sum;
        }
    }

    //前缀和数组
    public static class RangeSum2 {
        int[] presum;

        public RangeSum2(int[] arrary) {
            int N = arrary.length;
            presum = new int[N];
            presum[0] = arrary[0];
            for (int i = 1; i < N; i++) {
                presum[i] = presum[i - 1] + arrary[i];
            }
        }

        public int rangeSum(int L, int R) {
            return L == 0 ? presum[R] : presum[R] - presum[L - 1];
        }
    }

    public static void main(String[] args) {
        int[] arrary = new int[]{2, 4, 6, 5, 7, 4, 1, 5};
        RangeSum2 preArrary = new RangeSum2(arrary);
        int presumNum = preArrary.rangeSum(2, 3);  //pre[3]-pre[1]
        System.out.println("该区间类的和为：" + presumNum);
        System.out.println("############");

        RangeSum1 preArr = new RangeSum1(arrary);
        presumNum = preArr.rangeSum(2, 3);
        System.out.println("该区间类的和为：" + presumNum);
        System.out.println("############");


    }
}
