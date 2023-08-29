package day07_Morris二叉树的遍历;

/**
 * @author:fish
 * @date: 2023/4/25-8:51
 * @content:
 */
public class code02_IsBST {
    public static class Node {
        int value;
        Node left;
        Node right;

        Node(int v) {
            value = v;
        }
    }

    public static void morris(Node head) {
        if (head == null)
            return;
        Node cur = head;
        Node mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                }else {
                    mostRight.right = null;
                }
            }
            cur = cur.right;
        }
    }
    public static boolean isBST(Node head) {
        if (head == null) {
            return true;
        }
        Node cur = head;
        Node mostRight = null;
        Integer pre = null;
        boolean ans =true;
        while (cur != null) {
            // cur有没有左树
            mostRight = cur.left;
            if (mostRight != null) { // 有左树的情况下
                // 找到cur左树上，真实的最右
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                // 从while中出来，mostRight一定是cur左树上的最右节点
                // mostRight
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else { // mostRight.right != null -> mostRight.right == cur
                    mostRight.right = null;
                }
            }

            if (pre != null && pre >= cur.value)
                return false;
            pre= cur.value;
            cur = cur.right;
        }
        return true;
    }

    public static void main(String[] args) {
        Node head = new Node(4);
        head.left = new Node(2);
        head.right = new Node(6);
        head.left.left = new Node(1);
        head.left.right = new Node(3);
        head.right.left = new Node(5);
        head.right.right = new Node(7);
        System.out.println(isBST(head));
    }

}
