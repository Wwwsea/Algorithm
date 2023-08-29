package day1_计算机数和简单排序;

/**
 * @author:fish
 * @date: 2023/2/3-15:37
 * @content: 原码：最高位为符号位，0正1负
 * 反码：正数与原码一致；负数最高位不变，其余对源码按位取反
 * 补码：正数与原码一致；负数为该数的反码加1
 */
public class code1_print32 {
    public static void print(int num) {
        for (int i = 31; i >= 0; i--) {
            System.out.print((num & (1L << i)) == 0 ? "0" : "1");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int aa = 12;
        int bb = -12;
        print(aa);
//        print(~aa);
        print(bb);
//        print(~bb);
        System.out.println("---&----");
        print(aa & bb);
        System.out.println("---|----");
        print(aa | bb);
        System.out.println("---^----");
        print(aa ^ bb);
        System.out.println("---%----"+ 5.1%4);


        System.out.println("-------");
        print(Integer.MIN_VALUE);
        print(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);
        System.out.println(Integer.MAX_VALUE);

        System.out.println("-------");
        print(bb >> 1); //带符号右移
        print(bb >>> 1); //不带符号右移，补零

        System.out.println("---求反数----");
        int cc = 15;
        int dd = ~cc + 1;
        System.out.println(dd);

        System.out.println(001 | 111);

    }
}
