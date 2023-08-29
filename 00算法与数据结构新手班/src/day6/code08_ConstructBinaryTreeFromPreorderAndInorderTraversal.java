package day6;

import java.util.HashMap;

/**
 * @author:fish
 * @date: 2023/2/10-13:48
 * @content: 根据中序 先序数组 ，还原二叉树
 * 测试地址：https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 */
public class code08_ConstructBinaryTreeFromPreorderAndInorderTraversal {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    //方法一 ：使用了循环获得根节点的位置
    public TreeNode buildTree1(int[] pre, int[] in) {
        if (pre == null || in == null || pre.length != in.length)
            return null;
        return f(pre, 0, pre.length - 1, in, 0, in.length - 1);
    }

    //pre[l1...r1]  in[l2...r2]
    public TreeNode f(int[] pre, int l1, int r1, int[] in, int l2, int r2) {
        if (l1 > r1)
            return null;
        TreeNode head = new TreeNode(pre[l1]);
        if (l1 == r1)
            return head;
        int find = l2;
        while (in[find] != pre[l1]) {
            find++;
        }
        head.left = f(pre, l1 + 1, l1 + find - l2, in, l2, find - 1);
        head.right = f(pre, l1 + find - l2 + 1, r1, in, find + 1, r2);


        return head;
    }
    //使用哈希map存储后序遍历数组中每个节点所在位置
    public TreeNode buildTree2(int[] pre,int[] in){
        if (pre==null||in==null||pre.length!=in.length)
            return null;

        HashMap<Integer,Integer> map=new HashMap<>();
        for (int i = 0; i < in.length; i++) {
            map.put(in[i],i);
        }
        return g(pre,0,pre.length-1,in,0,in.length-1,map);
    }
    public TreeNode g(int[] pre,int l1,int r1,int[] in ,int l2,int r2, HashMap<Integer,Integer> map){
        if (l1>r1)
            return null;
        TreeNode root=new TreeNode(pre[l1]);
        if (l1==r1)
            return root;

        int find=map.get(pre[l1]);
        root.left=g(pre,l1+1,l1+find-l2,in,l2,find-1,map);
        root.right=g(pre,l1+find-l2+1,r1,in,find+1,r2,map);
        return root;

    }
}
