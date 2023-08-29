package day4_链表;

/**
 * @author:fish
 * @date: 2023/2/5-19:52
 * @content: 单 双链表的反转
 */
public class code1_reverseLinked {
    //单链表定义
    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            value = data;
        }
    }

    // 双链表定义
    public static class DoubleNode {
        public int value;
        DoubleNode next;
        DoubleNode last;

        public DoubleNode(int v) {
            value = v;
        }
    }

    // 单链表反转  (过河拆桥)
    public static Node reverseLinkedList(Node head) {
        Node pre = null;
        Node next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    //双链表反转
    public static DoubleNode reverseDoubleLinkedList(DoubleNode head) {
        DoubleNode next = null;
        DoubleNode pre = null;

        while (head != null) {
            next = head.next;
            head.next = pre;
            head.last = next;
            pre = head;
            head = next;
        }
        return pre;
    }

    public static void main(String[] args) {
        Node node1 = new Node(1);
        node1.next = new Node(2);
        node1.next.next = new Node(3);
        System.out.println("原head指向:" + node1.value);
        node1 = reverseLinkedList(node1);

        DoubleNode node2 = new DoubleNode(5);
        node2.next = new DoubleNode(6);
        node2.next.next = new DoubleNode(7);
        node2 = reverseDoubleLinkedList(node2);

        while (node1 != null) {
            System.out.print(node1.value + " ");
            node1 = node1.next;
        }
        System.out.println();

        while (node2 != null) {
            System.out.print(node2.value + " ");
            node2 = node2.next;
        }
    }
}
