package day7;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author:fish
 * @date: 2023/2/11-12:07
 * @content: 给出一棵二叉树的根，返回其节点值的自下而上的级别顺序遍历。(即从左到右，从叶到根逐级递增）
 *  测试地址：https://leetcode.com/problems/binary-tree-level-order-traversal-ii/
 */
public class code01_levelOrderBottom {
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> ans = new LinkedList<>();
        if (root == null)
            return ans;

        //队列queue存放每一层的节点,size位当前队列的长度
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int size;
        while (!queue.isEmpty()) {
            size = queue.size();
            //curAns存放当前队列的值。
            List<Integer> curAns = new LinkedList<>();
            //循环size次，弹出节点,收集当前值到curAns里，有左先加左，后右
            for (int i = 0; i < size; i++) {
                TreeNode curNode = queue.poll();
                curAns.add(curNode.val);
                if (curNode.left != null)
                    queue.add(curNode.left);
                if (curNode.right != null)
                    queue.add(curNode.right);
            }
            ans.add(0, curAns);
        }
        return ans;

    }
}
