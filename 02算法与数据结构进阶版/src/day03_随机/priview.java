package day03_随机;

import java.util.Random;

/**
 * @author:fish
 * @date: 2023/4/14-9:16
 * @content:
 */
public class priview {
    public static int[] reservoir(int[] nums, int k){
        int[] res=new int[k];
        for (int i = 0; i < k; i++) {
            res[i]=nums[i];
        }
        Random random = new Random();
        for (int i = 0; i < nums.length; i++) {
            int j=random.nextInt(i+1);
            if (j<k)
                res[j]=nums[i];
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums=new int[]{11,22,33,44,55,66,77,88,99,100,110};
        int luckyBolls=5;
        int[] res=reservoir(nums,luckyBolls);
        for (int re : res) {
            System.out.print(re+" ");
        }
    }
}
