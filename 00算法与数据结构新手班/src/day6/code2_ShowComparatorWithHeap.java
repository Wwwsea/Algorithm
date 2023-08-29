package day6;

import java.util.Calendar;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author:fish
 * @date: 2023/2/9-12:17
 * @content:
 * PriorityQueue(优先队列)实质上就是最小堆。
 * 增删改查时间复杂度：O(logN)
 */
public class code2_ShowComparatorWithHeap {
    //自定义比较器，升序
    //-1 ,第一个值在前
    //1 ,第二个值在前
    //0 ,无所谓
    public static class BigHeapComparator implements Comparator<Integer>{
        @Override
        public int compare(Integer o1, Integer o2) {
            if (o1<o2)
                return 1;
            else if (o1>o2)
                return -1;
            else return 0;
        }
    }
    public static void main(String[] args) {
        PriorityQueue<Integer> heap =new PriorityQueue<>(new BigHeapComparator());
        heap.add(14);
        heap.add(36);
        heap.add(31);
        heap.add(32);
        heap.add(56);
        System.out.println(heap.peek()); //不弹出，返回最小值；
        System.out.println("=======");
        while (!heap.isEmpty()){
            System.out.println(heap.poll()); //弹出，返回最小值；
        }

    }
}
