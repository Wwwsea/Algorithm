package day6;

/**
 * @author:fish
 * @date: 2023/2/10-11:31
 * @content: 如果两棵二叉树在结构上完全相同，并且节点具有相同的值，则认为它们是相同的。
 * 测试地址：https://leetcode.com/problems/same-tree/
 */
public class code05_SameTree {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null ^ q == null) {
            return false;
        }
        if (p == null && q == null)
            return true;
        return p.val==q.val &&isSameTree(p.left,q.left) &&isSameTree(p.right,q.right);

    }
}
