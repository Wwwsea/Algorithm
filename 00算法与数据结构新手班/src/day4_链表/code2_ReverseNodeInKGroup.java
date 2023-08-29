package day4_链表;

/**
 * @author:fish
 * @date: 2023/2/6-11:51
 * @content:
 * 实现在k区间长度的范围内反转；
 * 如[1,2,3,4,5]->[2,1,4,3,5]
 */
public class  code2_ReverseNodeInKGroup {
    public static class ListNode {
        public int value;
        public ListNode next;
    }

    //代码测试地址：https://leetcode.com/problems/reverse-nodes-in-k-group/
    //得到该区域类第k个节点
    //      a->b->c->d
    // k:   ↑ 2↑ 1↑    若k=3 则返回c节点
    public static ListNode getKGroupEnd(ListNode start, int k) {
        while (--k != 0 && start != null) {
            start = start.next;
        }
        return start;
    }

    //局部反转
    public static void reverse(ListNode start, ListNode end) {
        end = end.next;
        ListNode pre = null;
        ListNode next = null;
        ListNode cur = start;
        while (cur != end) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        start.next = end;
    }

    //整体反转;
    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode start = head;
        ListNode end = getKGroupEnd(start, k);
        if (end == null) {
            return head;
        }
        head = end;
        reverse(start, end);
        //凑齐第一组，第一组的原尾节点变为新链表的头节点；

        ListNode lastEnd = start;
        while (lastEnd.next != null) {
            start = lastEnd.next;
            end = getKGroupEnd(start, k);
            if (end == null)
                return head;
            reverse(start, end);
            lastEnd.next = end;
            lastEnd = start;
        }
        return head;

    }

    public static void main(String[] args) {
        int[] test =new int[] {11,22,33,44,55};
        int k = 3;
        int n = 0;
        while (--k != 0){
            n++;
        }
        System.out.print(test[n]+" ");
    }
}
