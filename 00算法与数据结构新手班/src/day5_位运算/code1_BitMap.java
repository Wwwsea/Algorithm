package day5_位运算;

import java.util.HashSet;

/**
 * @author:fish
 * @date: 2023/2/8-14:50
 * @content:位图、位运算实现加减乘除
 * int:4byte*8bit=32位，一个int可判断32个数字的存在
 * long:64位
 * 位图定义：做出一集合判断数字是否存在
 * 位图好处：极大压缩空间
 * num>>6  --> num/(2^6)
 * num&63  --> num%64
 * (num<<3)|1 --> num*2^3+1
 * 位运算（>>,&,^,|）相较于普通运算（＋，-，*，/）快十倍左右
 *
 * 2^0 = 1
 * 2^1 = 2
 * 2^2 = 4
 * 2^3 = 8
 * 2^4 = 16
 * 2^5 = 32
 * 2^6 = 64
 * 2^7 = 128
 * 2^8 = 256
 * 2^9 = 512
 * 2^10 = 1024
 */
public class code1_BitMap {
    public static class BitMap {
        private long[] bits;

        public BitMap(int max) {
            //假设max为170 则存在 bits[3] 可以取到的范围(...~ 64*3-1) 中的第42位置上
            //(max+64)>>6 --> (max+64)/64
            bits = new long[(max + 64) >> 6];
        }

        public void add(int num) {
            //举例: 0000 0010 | 0000 1000 = 0000 1010
            //(num&63) --> num%64
            bits[num >> 6] |= (1L << (num & 63));
        }

        public void delete(int num) {
            //举例: 0000 1010 & (~0000 1000) = 0000 0010
            bits[num >> 6] &= ~(1L << (num & 63));
        }

        public boolean contains(int num) {
            //举例: 0000 0010 & 0000 1000 = 0 则8这个数字不存在。
            return (bits[num >> 6] & (1L << (num & 63))) != 0;
        }

    }

    public static void main(String[] args) {
        int testTimes = 10000;
        System.out.println("begin~");
        int max = 1777;
        BitMap bitMap = new BitMap(max);
        System.out.println(bitMap.bits.length);
        System.out.println("===================");
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < testTimes; i++) {
            int num = (int) (Math.random() * (max + 1));
            double decide = Math.random();
            if (decide < 0.333) {
                bitMap.add(num);
                set.add(num);
            } else if (decide < 0.666) {
                bitMap.delete(num);
                set.remove(num);
            } else {
                if (set.contains(num) != bitMap.contains(num)) {
                    System.out.println("OoPS!!");
                    break;
                }
            }
        }
        System.out.println("end!");
    }
}
