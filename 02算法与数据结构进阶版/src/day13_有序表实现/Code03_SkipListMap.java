package day13_有序表实现;

import java.util.ArrayList;
import java.util.TreeSet;

/**
 * @author:fish
 * @date: 2023/5/31-20:50
 * @content:  跳表
 */

public class Code03_SkipListMap {

    // 跳表的节点定义
    public static class SkipListNode<K extends Comparable<K>, V> {
        public K key;
        public V val;
        public ArrayList<SkipListNode<K, V>> nextNodes;

        public SkipListNode(K k, V v) {
            key = k;
            val = v;
            nextNodes = new ArrayList<SkipListNode<K, V>>();
        }

        // 遍历的时候，如果是往右遍历到的null(next == null), 遍历结束
        // 头(null), 头节点的null，认为最小
        // node  -> 头，node(null, "")  node.isKeyLess(!null)  true
        // node里面的key是否比otherKey小，true，不是false
        public boolean isKeyLess(K otherKey) {
            //  otherKey == null -> false
            return otherKey != null && (key == null || key.compareTo(otherKey) < 0);
        }

        public boolean isKeyEqual(K otherKey) {
            return (key == null && otherKey == null)
                    || (key != null && otherKey != null && key.compareTo(otherKey) == 0);
        }

    }

    public static class SkipListMap<K extends Comparable<K>, V> {
        private static final double PROBABILITY = 0.5; // < 0.5 继续做，>=0.5 停
        private SkipListNode<K, V> head;
        private int size; // 收集到多少种不同的key
        private int maxLevel;

        public SkipListMap() {
            head = new SkipListNode<K, V>(null, null);  // 初始化最左节点 不可用 默认为null
            head.nextNodes.add(null); // 第0层指向空
            size = 0;
            maxLevel = 0;
        }

        // 从最高层开始，一路找下去，
        // 最终，找到第0层的 < key 的最右的节点
        private SkipListNode<K, V> mostRightLessNodeInTree(K key) {
            if (key == null) {
                return null;
            }
            int level = maxLevel;
            SkipListNode<K, V> cur = head;
            while (level >= 0) { // 从上层跳下层
                cur = mostRightLessNodeInLevel(key, cur, level--);
            }
            return cur;
        }

        // 在level层里，如何往右移动
        // 现在来到的节点是cur，来到了cur的level层，在level层上，找到 < key 最后一个节点并返回
        private SkipListNode<K, V> mostRightLessNodeInLevel(K key,
                                                            SkipListNode<K, V> cur,
                                                            int level) {
            SkipListNode<K, V> next = cur.nextNodes.get(level);
            while (next != null && next.isKeyLess(key)) {
                cur = next;
                next = cur.nextNodes.get(level);
            }
            return cur;
        }

        public boolean containsKey(K key) {
            if (key == null) {
                return false;
            }
            SkipListNode<K, V> less = mostRightLessNodeInTree(key);
            SkipListNode<K, V> next = less.nextNodes.get(0);
            return next != null && next.isKeyEqual(key);
        }

        // 新增、改value
        public void put(K key, V value) {
            if (key == null) {
                return;
            }
            // 0层上，最右一个，< key 的Node -> >key
            SkipListNode<K, V> less = mostRightLessNodeInTree(key);
            SkipListNode<K, V> find = less.nextNodes.get(0);
            if (find != null && find.isKeyEqual(key)) {
                find.val = value;
            } else { // find == null
                size++;
                int newNodeLevel = 0;
                while (Math.random() < PROBABILITY) {
                    newNodeLevel++;
                }
                // newNodeLevel
                while (newNodeLevel > maxLevel) {
                    head.nextNodes.add(null);
                    maxLevel++;
                }
                SkipListNode<K, V> newNode = new SkipListNode<K, V>(key, value);
                for (int i = 0; i <= newNodeLevel; i++) {
                    newNode.nextNodes.add(null);
                }
                int level = maxLevel;
                SkipListNode<K, V> pre = head;
                while (level >= 0) {
                    // level从上往下, 在当前层中,找到最右的 < key 的节点
                    pre = mostRightLessNodeInLevel(key, pre, level);

                    // 若当前层小于新节点拥有的层数 则在pre的后面加入新节点
                    if (level <= newNodeLevel) {
                        newNode.nextNodes.set(level, pre.nextNodes.get(level));
                        pre.nextNodes.set(level, newNode);
                    }
                    level--;
                }
            }
        }

/*        public V get(K key) {
            if (key == null) {
                return null;
            }
            SkipListNode<K, V> less = mostRightLessNodeInTree(key);
            SkipListNode<K, V> next = less.nextNodes.get(0);
            return next != null && next.isKeyEqual(key) ? next.val : null;
        }*/

        public void remove(K key) {
            if (containsKey(key)) {
                size--;
                int level = maxLevel;
                SkipListNode<K, V> pre = head;
                while (level >= 0) {
                    pre = mostRightLessNodeInLevel(key, pre, level);  // 取到level这层 < key 的最右节点
                    SkipListNode<K, V> next = pre.nextNodes.get(level);  // 在level这层 < key 的下一个节点
                    // 1）在这一层中，pre下一个就是key
                    // 2）在这一层中，pre的下一个key是>要删除key
                    if (next != null && next.isKeyEqual(key)) {
                        // free delete node memory -> C++
                        // level : pre -> next(key) -> ...
                        pre.nextNodes.set(level, next.nextNodes.get(level));
                    }
                    /* 在level层只有一个节点了，就是默认节点head
                        若删完 为如下情形 level为2的行只有一个原始节点 则执行下面if里语句
                        删掉当前层，层数减一

                        (2) -> null
                        (1) -> (aa) ->      -> null
                        (0) -> (aa) -> (bb) -> null
                     */
                    if (level != 0 && pre == head && pre.nextNodes.get(level) == null) {
                        head.nextNodes.remove(level);
                        maxLevel--;
                    }
                    level--;
                }
            }
        }

        // (0) -> (aa) -> (bb) -> null        return : aa节点的key值
        public K firstKey() {

            return head.nextNodes.get(0) != null ? head.nextNodes.get(0).key : null;
        }

        // (0) -> (aa) -> (bb) -> null        return : bb节点的key值
        public K lastKey() {
            int level = maxLevel;
            SkipListNode<K, V> cur = head;
            while (level >= 0) {
                SkipListNode<K, V> next = cur.nextNodes.get(level);
                while (next != null) {
                    cur = next;
                    next = cur.nextNodes.get(level);
                }
                level--;
            }
            return cur.key;
        }

        // 距离key最近 >= key的值 往上↑ 取
        public K ceilingKey(K key) {
            if (key == null) {
                return null;
            }
            SkipListNode<K, V> less = mostRightLessNodeInTree(key);
            SkipListNode<K, V> next = less.nextNodes.get(0);
            return next != null ? next.key : null;
        }

        // 距离key最近 <= key的值 往下↓ 取
        public K floorKey(K key) {
            if (key == null) {
                return null;
            }
            SkipListNode<K, V> less = mostRightLessNodeInTree(key);
            SkipListNode<K, V> next = less.nextNodes.get(0);
            return next != null && next.isKeyEqual(key) ? next.key : less.key;
        }

        public int size() {
            return size;
        }

    }

    // for test
    public static void printAll(SkipListMap<String, String> obj) {
        for (int i = obj.maxLevel; i >= 0; i--) {
            System.out.print("Level " + i + " : ");
            SkipListNode<String, String> cur = obj.head;
            while (cur.nextNodes.get(i) != null) {
                SkipListNode<String, String> next = cur.nextNodes.get(i);
                System.out.print("(" + next.key + " , " + next.val + ") ");
                cur = next;
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        SkipListMap<String, String> test = new SkipListMap<>();
        printAll(test);
        System.out.println("======================");
        test.put("A", "10");
        printAll(test);
        System.out.println("======================");
        test.remove("A");
        printAll(test);
        System.out.println("======================");
        test.put("E", "E");
        test.put("B", "B");
        test.put("A", "A");
        test.put("F", "F");
        test.put("C", "C");
        test.put("D", "D");
        printAll(test);
        System.out.println("======================");
        System.out.println(test.containsKey("B"));
        System.out.println(test.containsKey("Z"));
        System.out.println(test.firstKey());
        System.out.println(test.lastKey());
        System.out.println(test.floorKey("D"));
        System.out.println(test.ceilingKey("D"));
        System.out.println("======================");
        test.remove("D");
        printAll(test);
        System.out.println("======================");
        System.out.println(test.floorKey("D"));
        System.out.println(test.ceilingKey("D"));

        TreeSet<Integer> set = new TreeSet<>();
        set.add(12);
        set.add(22);
        set.add(62);
        set.add(42);
        set.add(32);

        System.out.println("ceiling: "+set.ceiling(50));
        System.out.println("floor: "+set.floor(50));


    }

}
