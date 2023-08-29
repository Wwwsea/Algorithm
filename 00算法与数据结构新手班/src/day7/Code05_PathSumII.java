package day7;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.GZIPOutputStream;

/**
 * @author:fish
 * @date: 2023/2/13-11:09
 * @content: 给定二叉树的根和整数targetSum，返回路径中节点值之和等于targetSum的所
 * 有根到叶路径。每个路径都应该作为节点值的列表返回，而不是节点引用。
 * 测试地址: https://leetcode.com/problems/path-sum-ii
 */
public class Code05_PathSumII {
    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
    }
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> ans=new ArrayList<>();
        if (root==null)
            return ans;
        List<Integer> path=new ArrayList<>();
        process(root,path,0,targetSum,ans);
        return ans;

    }
    public static void process(TreeNode x,List<Integer> path,int preSum,int goal,List<List<Integer>> ans){
        if (x.left==null && x.right==null){
            if (preSum+x.val==goal){
                path.add(x.val);
                ans.add(copy(path));
                //需移除当前位置，否则在调用该节点的右节点时候、左节点的值没有抹除
                path.remove(path.size()-1);
            }
            return;
        }
        //非叶节点
        path.add(x.val);
        preSum+=x.val;
        if (x.left!=null)
            process(x.left,path,preSum,goal,ans);
        if (x.right!=null)
            process(x.right,path,preSum,goal,ans);
        path.remove(path.size()-1);
    }
    public static List<Integer> copy(List<Integer> path){
        List<Integer> ans=new ArrayList<>();
        for (Integer num:path){
            ans.add(num);
        }
        return ans;
    }
}
