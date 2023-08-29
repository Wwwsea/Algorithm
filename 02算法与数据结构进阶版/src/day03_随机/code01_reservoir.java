package day03_随机;

import java.util.Random;

/**
 * @author:fish
 * @date: 2023/4/13-19:34
 * @content: 蓄水池问题
 * 优势: 处理完所有数据时，蓄水池的每个数据都是以m/N的概率获得
 */
public class code01_reservoir {
    public static int[] reservoirSampling(int[] nums, int k) {
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = nums[i];
        }

        Random random = new Random();
        for (int i = 0; i < nums.length; i++) {
            int j = random.nextInt(i + 1); //生成0~i的随机整数
            if (j < k)
                res[j] = nums[i];
        }
        return res;
    }

    public static void main(String[] args) {
        int[] test = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] res = reservoirSampling(test, 5);
        for (int re : res) {
            System.out.print(re + " ");
        }

    }
}
