package day7;

/**
 * @author:fish
 * @date: 2023/2/11-13:12
 * @content: 平衡树：|左树-右数|<=1
 */
public class Code02_BalancedBinaryTree {
    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
    }
    //知道某个节点，返回是否平衡，和它的高度
    public class info{
        boolean isBalanced;
        int height;
        public info(boolean isBalanced,int height){
            this.isBalanced=isBalanced;
            this.height=height;
        }
    }
    public info process(TreeNode x){
        if (x==null)
            return new info(true,0);
        //节点的存在
        info leftInfo=process(x.left);
        info rightInfo=process(x.right);

        int height=Math.max(leftInfo.height, rightInfo.height)+1;
        boolean isBalance= leftInfo.isBalanced && rightInfo.isBalanced && Math.abs(leftInfo.height- rightInfo.height)<2;

        return new info(isBalance,height);
    }
    public boolean isBalanced(TreeNode root){
        return process(root).isBalanced;
    }
}
