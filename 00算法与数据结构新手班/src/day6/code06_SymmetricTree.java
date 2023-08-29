package day6;

/**
 * @author:fish
 * @date: 2023/2/10-12:08
 * @content: 给出二叉树的根，检查它是否是自己的镜像（即围绕其中心对称）。
 */
public class code06_SymmetricTree {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }
    public static boolean isSymmetric(TreeNode head){
        return isMirror(head,head);
    }
    public static boolean isMirror(TreeNode h1,TreeNode h2){
        if (h1==null ^ h2==null)
            return false;
        if (h1==null && h2==null)
            return true;
        return h1.val== h2.val && isMirror(h1.left,h2.right) && isMirror(h1.left, h2.right);
    }
}
