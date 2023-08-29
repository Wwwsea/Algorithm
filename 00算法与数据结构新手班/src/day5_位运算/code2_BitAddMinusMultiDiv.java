package day5_位运算;

/**
 * @author:fish
 * @date: 2023/2/8-19:16
 * @content: 位运算实现加减乘除
 * N/2 相当于N>>1; N*2+1 相当于（（N<<1）| 1）
 * 并且为了优先级的考虑 括号一定要注意 注意运算优先级 加减是优先于位运算的
 * 测试链接：https://leetcode.com/problems/divide-two-integers
 */
public class code2_BitAddMinusMultiDiv {

        public static int add(int a, int b) {
            int sum = a;
            while (b != 0) {
                sum = a ^ b;
                b = (a & b) << 1;
                a = sum;
            }
            return sum;
        }
        // 取反
        public static int negNum(int n) {
            return add(~n, 1);
        }
        // 减法
        public static int minus(int a, int b) {
            return add(a, negNum(b));
        }
        // 乘法
        public static int multi(int a, int b) {
            int res = 0;
            while (b != 0) {
                if ((b & 1) != 0) {
                    res = add(res, a);
                }
                a <<= 1;
                b >>>= 1;
            }
            return res;
        }

        public static boolean isNeg(int n) {
            return n < 0;
        }
        // 都先按照正数处理
        public static int div(int a, int b) {
            int x = isNeg(a) ? negNum(a) : a;
            int y = isNeg(b) ? negNum(b) : b;
            int res = 0;
            for (int i = 30; i >= 0; i = minus(i, 1)) {
                if ((x >> i) >= y) {
                    res |= (1 << i);
                    x = minus(x, y << i);
                }
            }
            return isNeg(a) ^ isNeg(b) ? negNum(res) : res;
        }
        // 除
        public static int divide(int a, int b) {
            if (a == Integer.MIN_VALUE && b == Integer.MIN_VALUE) {
                return 1;
            } else if (b == Integer.MIN_VALUE) {
                return 0;
            } else if (a == Integer.MIN_VALUE) {
                if (b == negNum(1)) {
                    return Integer.MAX_VALUE;
                } else {
                    int c = div(add(a, 1), b);
                    return add(c, div(minus(a, multi(c, b)), b));
                }
            } else {
                return div(a, b);
            }
        }


    public static void main(String[] args) {
        System.out.println(negNum(9));

        System.out.println(divide(6, 3));
    }

}
