package day1_计算机数和简单排序;

/**
 * @author:fish
 * @date: 2023/2/3-20:21
 * @content:计算阶乘
 */
public class code2_factorial {
    public static long f(int N) {
        long ans = 0;
        long cur = 1;
        for (int i = 1; i <= N; i++) {
            cur = cur * i;
            ans += cur;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(f(10));
    }
}
