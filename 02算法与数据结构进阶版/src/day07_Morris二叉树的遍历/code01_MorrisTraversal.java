package day07_Morris二叉树的遍历;

/**
 * @author:fish
 * @date: 2023/4/21-12:07
 * @content: Morris遍历二叉树(中序，先序，后序，判断是否为平衡二叉树) 时间复杂度O(N)
 * 空间复杂度 O(h) 优化成 O(1)
 */
public class code01_MorrisTraversal {
    public static class Node {
        int value;
        Node left;
        Node right;

        Node(int v) {
            value = v;
        }
    }

    public static void morrisPre(Node head) {
        if (head == null)
            return;
        Node cur = head;
        Node mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) { // 有左孩子 大于第一次到达值
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) { // 第一次来到
                    mostRight.right = cur;
                    System.out.print(cur.value + " ");
                    cur = cur.left;
                    continue;
                } else {// mostRight.right == cur 第二次来到
                    mostRight.right = null;
                }
            } else { // 无左孩子 直接打印
                System.out.print(cur.value + " ");
            }
            cur = cur.right;
        }
        System.out.println();
    }

    public static void morrisIn(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) { // 有左孩子 打印第二次到达值
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            }
            System.out.print(cur.value + " ");
            cur = cur.right;
        }
        System.out.println();
    }

    public static void morrisPos(Node head) {
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
                } else {
                    mostRight.right = null;
                    printEdge(cur.left); // 逆序打印 当前节点的左树右边界
                }
            }
            cur = cur.right;
        }
        printEdge(head);
        System.out.println();
    }

    //逆序打印节点的有边界
    public static void printEdge(Node head) {
        Node tail = reverseEdge(head);
        Node cur = tail;
        while (cur != null) {
            System.out.print(cur.value + " ");
            cur = cur.right;
        }
        reverseEdge(tail);
    }

    public static Node reverseEdge(Node head) {
        Node pre = null;
        Node next = null;
        while (head != null) {
            next = head.right;
            head.right = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    // for test
    public static void pos(Node head) {
        if (head == null)
            return;
        pos(head.left);
        pos(head.right);
        System.out.print(head.value + " ");
    }
    // for test
    public static void printTree(Node head){
        if (head==null)
            return;
        printInOrder(head,0,"H",17);
        System.out.println();

    }
    public static void printInOrder(Node head, int height, String to, int len){
        if (head==null)
            return;
        printInOrder(head.left,height+1,"V",len);
        String val = to+head.value+to;
        int lenM = val.length();
        int lenL = (len - lenM)>>1;
        int lenR = (len-lenM-lenL);
        val=getSpace(lenL)+val+getSpace(lenR);
        System.out.println(getSpace(height*len)+val);
        printInOrder(head.right,height+1,"^",len);

    }
    public static String getSpace(int num){
        String space = " ";
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < num; i++) {
            sb.append(space);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Node head = new Node(4);
        head.left = new Node(2);
        head.right = new Node(6);
        head.left.left = new Node(1);
        head.left.right = new Node(3);
        head.right.left = new Node(5);
        head.right.right = new Node(7);

        printTree(head);
        morrisPre(head);
        morrisIn(head);
        morrisPos(head);
        pos(head);
    }

}
