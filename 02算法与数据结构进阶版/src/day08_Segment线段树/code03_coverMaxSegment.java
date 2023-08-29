package day08_Segment线段树;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author:fish
 * @date: 2023/4/25-19:04
 * @content: 线段的最大覆盖问题
 */
public class code03_coverMaxSegment {
    public static int maxCoverBao(int[][] m) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < m.length; i++) {
            min = Math.min(min, m[i][0]);
            max = Math.max(max, m[i][1]);
        }
        int cover = 0;
        for (double i = min + 0.5; i < max; i++) {
            int cur = 0;
            for (int j = 0; j < m.length; j++) {
                if (m[j][0] < i && m[j][1] > i) {
                    cur++;
                }
            }
            cover = Math.max(cur, cover);
        }
        return cover;
    }

    public static class Line {
        int start;
        int end;

        Line(int s, int e) {
            start = s;
            end = e;
        }
    }

    public static int maxCover(int[][] m) {
        Line[] lines = new Line[m.length];
        for (int i = 0; i < lines.length; i++) {
            lines[i] = new Line(m[i][0], m[i][1]);
        }
        // 按起始值升序排序
        Arrays.sort(lines, new StartComparator());
        // 在小根堆中 按尾值升序
        PriorityQueue<Line> heap = new PriorityQueue<>(new EndComparator());
        int max = 0;
        for (int i = 0; i < lines.length; i++) {
            while (!heap.isEmpty() && heap.peek().end <= lines[i].start) {
                heap.poll();
            }
            heap.add(lines[i]);
            max = Math.max(max, heap.size());
        }
        return max;
    }

    public static class StartComparator implements Comparator<Line> {
        @Override
        public int compare(Line o1, Line o2) {
            return o1.start - o2.start;
        }
    }

    public static class EndComparator implements Comparator<Line> {
        @Override
        public int compare(Line o1, Line o2) {
            return o1.end - o2.end;
        }
    }

    public static int[][] generateLines(int maxSize, int L, int R) {
        int size = (int) (Math.random() * maxSize + 1);
        int[][] m = new int[size][2];
        for (int i = 0; i < m.length; i++) {
            int a = (int) (L + Math.random() * (R - L + 1));
            int b = (int) (L + Math.random() * (R - L + 1));

            if (a == b)
                b = a + 1;
            m[i][0] = Math.min(a, b);
            m[i][1] = Math.max(a, b);
        }
        return m;
    }

    public static void main(String[] args) {
        int maxSize=10;
        int L = 5;
        int R = 25;
        int testTimes=50000;
        for (int i = 0; i < testTimes; i++) {
            int[][] m = generateLines(maxSize, L, R);
            int test1 = maxCoverBao(m);
            int test2 = maxCover(m);
            if (test1 != test2)
                System.out.println("Oops~");
        }
        System.out.println("Succeed !");

    }
}
