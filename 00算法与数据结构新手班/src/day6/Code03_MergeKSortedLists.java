package day6;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author:fish
 * @date: 2023/2/9-14:42
 * @content:
 * 构建一个小根堆，维护每个链表未被合并的第一个节点，每次取出堆顶元素（值最小），然后将其下一个节点的值插入堆中。
 * 时间复杂度：O(K*lonN)
 *
 * 测试链接：https://leetcode.com/problems/merge-k-sorted-lists/
 */
public class Code03_MergeKSortedLists {
    public static class ListNode{
        public int val;
        public ListNode next;
    }
    //自定义比较器，负数--第一值在前，升序；
    public static class ListNodeComparator implements Comparator<ListNode>{

        @Override
        public int compare(ListNode o1, ListNode o2) {
            return o1.val-o2.val;
        }
    }

    //lists中存放 每个链表的头节点
    public static ListNode mergeKLists(ListNode[] lists){
        if (lists==null)
            return null;
//        PriorityQueue<ListNode> heap=new PriorityQueue<>(new ListNodeComparator());
        PriorityQueue<ListNode> heap = new PriorityQueue<>((o1, o2) -> o1.val- o2.val);
        //将所有头节点放进小根堆。
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null)
                heap.add(lists[i]);

        }

        if (heap.isEmpty()) {
            return null;
        }

        ListNode head=heap.poll();
        ListNode pre=head;

        //head的下一个节点不为空就加入；前面加入了k个头节点 定义head后弹出一个值 现在小根堆的数目位k-1，因为是k条链表，一开始必须数k个数在比较
        //否则会遗漏一条链表没有排序。
        if (pre.next!=null){
            heap.add(pre.next);
        }
        ListNode cur;
        while (!heap.isEmpty()){
            cur= heap.poll();

            pre.next=cur;

            pre=cur;

            //当前节点下的后一个节点不为空，则就加入在heap中重新排序
            if (cur.next!=null)
                heap.add(cur.next);
        }
        return head;
    }
}
