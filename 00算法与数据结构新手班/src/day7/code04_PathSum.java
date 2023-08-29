package day7;

/**
 * @author:fish
 * @date: 2023/2/11-19:44
 * @content:
 */
public class code04_PathSum {
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public boolean isSum = false;

    //非递归
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null)
            return false;
        isSum = false;
        process(root, 0, sum);
        return isSum;

    }

    public void process(TreeNode x, int preSum, int goal) {
        //x为叶节点
        if (x.left == null && x.right == null) {
            if (x.val + preSum == goal)
                isSum = true;
        }
        preSum += x.val;
        if (x.left != null)
            process(x.left, preSum, goal);
        if (x.right != null)
            process(x.right, preSum, goal);

    }

    //递归   rest从尾减到头
    public static boolean process(TreeNode root, int rest) {
        //当前节点为叶节点
        if (root.left == null && root.right == null)
            return root.val == rest;
        boolean ansLeft = root.left != null ? process(root.left, rest - root.val) : false;
        boolean ansRight = root.right != null ? process(root.right, rest - root.val) : false;
        boolean ans = ansLeft | ansRight;
        return ans;
    }

    public static boolean hasPathSum2(TreeNode root, int sum) {
        if (root == null)
            return false;
        return process(root, sum);
    }
}
