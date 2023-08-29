package day4_链表;

/**
 * @author:fish
 * @date: 2023/2/6-14:01
 * @content: 两链表求和 如：2->4->3   +  5->6->4   ==  7->0->8
 * 测试地址：https://leetcode.com/problems/add-two-numbers/
 */
public class code3_AddTwoNumber {
    public static class ListNode {
        public int val;
        public ListNode next;

        public ListNode(int val) {
            this.val = val;
        }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static ListNode addTwoNumbers(ListNode head1, ListNode head2) {
        int len1 = listLength(head1);
        int len2 = listLength(head2);
        ListNode l = len1 >= len2 ? head1 : head2;
        ListNode s = l == head1 ? head2 : head1;

        ListNode curl = l;
        ListNode curs = s;
        ListNode last = curl;//长链表的小跟班，记录最后一位数的位置，方便进位

        int carry = 0; //进位数字
        int curNum = 0; //当前数字和

        while (curs != null) {
            curNum = curl.val + curs.val + carry;
            curl.val = curNum % 10;
            carry = curNum / 10;
            last = curl;
            curl = curl.next;
            curs = curs.next;
        }

        while (curl != null) {
            curNum = curl.val + carry;
            curl.val = curNum % 10;
            carry = curNum / 10;
            last = curl;
            curl = curl.next;
        }

        while (carry != 0) {
            last.next = new ListNode(1);
        }

        return l;
    }

    public static int listLength(ListNode head) {
        int len = 0;
        while (head != null) {
            len++;
            head = head.next;
        }
        return len;
    }
}
