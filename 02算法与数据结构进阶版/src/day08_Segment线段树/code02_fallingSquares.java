package day08_Segment线段树;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

/**
 * @author:fish
 * @date: 2023/4/25-14:42
 * @content: 类似俄罗斯方块游戏
 * 有个无线延申的x轴，给定数组[A,B]表示延x等于A轴降落 正方形的大小为B*B，正方形间不存在重力，求最大高度
 * 假定每次方块坠落的位置为[A,A+B-1],算出当前高度,求max
 * leetcode测试地址: https://leetcode.cn/problems/falling-squares/submissions/
 */
public class code02_fallingSquares {
    public static class SegmentTree {
        int[] max;
        int[] change;
        boolean[] update;

        public SegmentTree(int size) {
            int N = size + 1; //零位置舍弃
            max = new int[N << 2];
            change = new int[N << 2];
            update = new boolean[N << 2];
        }

        public void pushUp(int rt) {
            max[rt] = Math.max(max[rt << 1], max[rt << 1 | 1]);
        }

        public void pushDown(int rt) {
            if (update[rt]) {
                change[rt << 1] = change[rt];
                change[rt << 1 | 1] = change[rt];
                update[rt << 1] = true;
                update[rt << 1 | 1] = true;
                max[rt << 1] = change[rt];
                max[rt << 1 | 1] = change[rt];
                update[rt] = false;
            }
        }

        public void update(int L, int R, int C, int l, int r, int rt) {
            if (L <= l && R >= r) {
                update[rt] = true;
                change[rt] = C;
                max[rt] = C;
                return;
            }
            int mid = (l + r) >> 1;
            pushDown(rt);
            if (L <= mid)
                update(L, R, C, l, mid, rt<<1);
            if (R > mid)
                update(L, R, C, mid + 1, r, rt<<1 | 1);
            pushUp(rt);
        }

        public int query(int L, int R, int l, int r, int rt) {
            if (L <= l && r <= R) {
                return max[rt];
            }
            int mid = (l + r) >> 1;
            pushDown(rt);
            int left = 0;
            int right = 0;
            if (L <= mid) {
                left = query(L, R, l, mid, rt << 1);
            }
            if (R > mid) {
                right = query(L, R, mid + 1, r, rt << 1 | 1);
            }
            return Math.max(left, right);
        }

    }

    //离散化
    public static HashMap<Integer, Integer> index(int[][] position) {
        TreeSet<Integer> pos = new TreeSet<>();
        for (int[] arr : position) {
            pos.add(arr[0]);
            pos.add(arr[0] + arr[1] - 1);
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        int count = 0;
        for (Integer index : pos) {
            map.put(index, ++count);
        }
        return map;
    }

    public List<Integer> fallingSquares(int[][] positions) {
        // 100   -> 1    306 ->   2   403 -> 3
        // [100,403]   1~3
        HashMap<Integer, Integer> map = index(positions);
        int N = map.size();
        SegmentTree seg = new SegmentTree(N);
        List<Integer> res = new ArrayList<>();
        int max = 0;
        // 每落一个正方形 收集当前高度，更新最大高度
        for (int[] arr : positions) {
            int L = map.get(arr[0]);
            int R = map.get(arr[0] + arr[1] - 1);
            int height = seg.query(L, R, 1, N, 1) + arr[1];
            max = Math.max(max, height);
            res.add(max);
            seg.update(L, R, height, 1, N, 1);
        }
        return res;
    }
}
