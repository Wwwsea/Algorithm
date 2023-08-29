package day6;

/**
 * @author:fish
 * @date: 2023/2/10-12:24
 * @content: 求树的最大深度
 */
public class code07_MaxDepth {
    public static class TreeNode{
        public int val;
        public TreeNode left;
        public TreeNode right;
    }
    public static int maxDepth(TreeNode head){
        if (head==null)
            return 0;
        return Math.max(maxDepth(head.left),maxDepth(head.right))+1;
    }
}
