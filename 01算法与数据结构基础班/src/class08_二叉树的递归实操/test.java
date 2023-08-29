package class08_二叉树的递归实操;

import java.util.ArrayList;

/**
 * @author:fish
 * @date: 2023/8/6-9:07
 * @content: 给定二叉树头节点 返回该二叉树中 子树为最大搜索二叉树的头节点
 * 搜索二叉树： 左值 < 头值 < 右值
 */
public class test {
    public static class Node{
        int value;
        Node left;
        Node right;

        Node(int val){
            value = val;
        }
    }
    public static class Info{
        Node maxSubBSTHead;
        int maxSubBSTSize;
        int min;
        int max;
        Info(Node h,int s,int i,int a){

            maxSubBSTHead = h;
            maxSubBSTSize = s;
            min = i;
            max = a;
        }
    }
    public static Info process(Node X){
        if (X == null){
            return null;
        }
        Info leftInfo = process(X.left);
        Info rightInfo = process(X.right);
        int min = X.value;
        int max = X.value;
        Node maxSubBSTHead = null;
        int maxSubBSTSize = 0;
        if (leftInfo!=null){
            min = Math.min(min, leftInfo.min);
            max = Math.max(max, leftInfo.min);
            maxSubBSTHead = leftInfo.maxSubBSTHead;
            maxSubBSTSize = leftInfo.maxSubBSTSize;
        }
        if (rightInfo!=null){
            min = Math.min(min, rightInfo.min);
            max = Math.max(max, rightInfo.max);
            if (rightInfo.maxSubBSTSize > maxSubBSTSize){
                maxSubBSTHead = rightInfo.maxSubBSTHead;;
                maxSubBSTSize = rightInfo.maxSubBSTSize;;
            }
        }

        if ((leftInfo == null ? true : (leftInfo.maxSubBSTHead == X.left && leftInfo.max<X.value) )
                &&(rightInfo == null ? true : (rightInfo.maxSubBSTHead ==X.right && rightInfo.min>X.value)) ){
            maxSubBSTHead = X;
            maxSubBSTSize = (leftInfo==null ? 0 : leftInfo.maxSubBSTSize)
                            +(rightInfo==null ? 0 : rightInfo.maxSubBSTSize)+1;
        }
        return new Info(maxSubBSTHead,maxSubBSTSize,min,max);

    }

    public static int getBSTSize(Node head) {
        if (head == null) {
            return 0;
        }
        ArrayList<Node> arr = new ArrayList<>();
        in(head, arr);
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i).value <= arr.get(i - 1).value) {
                return 0;
            }
        }
        return arr.size();
    }

    public static void in(Node head, ArrayList<Node> arr) {
        if (head == null) {
            return;
        }
        in(head.left, arr);
        arr.add(head);
        in(head.right, arr);
    }
    public static Node maxSubBSTHead2(Node head){
        if (head == null){
            return null;
        }
        return process(head).maxSubBSTHead;
    }

    public static Node maxSubBSTHead1(Node head) {
        if (head == null) {
            return null;
        }
        if (getBSTSize(head) != 0) {
            return head;
        }
        Node leftAns = maxSubBSTHead1(head.left);
        Node rightAns = maxSubBSTHead1(head.right);
        return getBSTSize(leftAns) >= getBSTSize(rightAns) ? leftAns : rightAns;
    }


    // for test
    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static void main(String[] args) {
        int maxLevel = 4;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (maxSubBSTHead1(head) != maxSubBSTHead2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
