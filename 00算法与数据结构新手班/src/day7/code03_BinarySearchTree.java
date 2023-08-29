package day7;

/**
 * @author:fish
 * @date: 2023/2/11-18:28
 * @content: 给出一棵二进制树的根，确定它是否是一棵有效的二进制搜索树（BST）
 * 搜索树: 左子树值 < 头节点值 < 右子树值
 */
public class code03_BinarySearchTree {
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public class Info {
        public boolean isSearched;
        public int min;
        public int max;

        public Info(Boolean isSearched, int min, int max) {
            this.isSearched = isSearched;
            this.max = max;
            this.min = min;
        }
    }

    public Info process(TreeNode x) {
        if (x == null)
            return null;

        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);


        int min = x.val;
        int max = x.val;

        if (leftInfo != null) {
            max = Math.max(leftInfo.max, max);
            min = Math.min(leftInfo.min, min);
        }
        if (rightInfo != null) {
            max = Math.max(rightInfo.max, max);
            min = Math.min(rightInfo.max, min);
        }

        boolean isSearched = false;
        //判断其子树是否为搜索二叉树
        boolean leftIsSearched = leftInfo == null ? true : (leftInfo.isSearched);
        boolean rightIsSearched = rightInfo == null ? true : (rightInfo.isSearched);
        //判断当前节点是否是二叉树 左子树的max < 头节点 < 右子树的min
        boolean leftMaxLessX = leftInfo == null ? true : (leftInfo.max < x.val);
        boolean rightMinMoreX = rightInfo == null ? true : (rightInfo.min > x.val);

        if (leftIsSearched && rightIsSearched && leftMaxLessX && rightMinMoreX)
            isSearched = true;
        return new Info(isSearched, min, max);
    }

    public boolean isValidBST(TreeNode root) {
        return process(root).isSearched;
    }
}
