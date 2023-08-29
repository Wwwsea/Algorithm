import java.util.HashMap;
import java.util.Map;

/**
 * @author:fish
 * @date: 2023/8/9-17:49
 * @content: 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
 * 示例 1：
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[0,1]
 * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
 * 示例 2：
 * 输入：nums = [3,2,4], target = 6
 * 输出：[1,2]
 * 示例 3:
 * 输入：nums = [3,3], target = 6
 * 输出：[0,1]
 */
public class code01_sumNum {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target-nums[i])){
                return new int[]{i,map.get(target)};
            }
            map.put(nums[i],i);
        }
        return null;
    }
}
