package day6;

/**
 * @author:fish
 * @date: 2023/2/10-11:01
 * @content:
 */
public class code04_TraversalBinaryTree {
    //定义二叉树
    public static class Node{
        public int value;
        public Node left;
        public Node right;
        public Node(int value){
            this.value=value;
        }
    }
    //先序 头左右
    public static void pre(Node head){
        if (head==null)
            return;
        System.out.print(" "+head.value);
        pre(head.left);
        pre(head.right);
    }
    //中序 左头右
    public static void in(Node head){
        if (head==null)
            return;
        in(head.left);
        System.out.print(" "+head.value);
        in(head.right);
    }
    //后序 左右头
    public static void pos(Node head){
        if (head==null)
            return;
        pos(head.left);
        pos(head.right);
        System.out.print(" "+head.value);
    }

    //递归序 ！每个节点都遍历了三回 ！
    public static void f(Node head){
        if (head==null)
            return;
        //(1)
        f(head.left);
        //(2)
        f(head.right);
        //(3)
    }

    public static void main(String[] args) {
        Node node=new Node(1);
        node.left=new Node(2);
        node.right=new Node(3);
        node.left.left=new Node(4);
        node.left.right=new Node(5);
        node.right.left=new Node(6);
        node.right.right=new Node(7);

        System.out.print("+++++先序+++++");
        pre(node);
        System.out.println();
        System.out.print("+++++中序+++++");
        in(node);
        System.out.println();
        System.out.print("+++++后序+++++");
        pos(node);
    }
}
