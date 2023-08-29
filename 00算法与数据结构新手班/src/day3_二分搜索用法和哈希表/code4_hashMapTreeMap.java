package day3_二分搜索用法和哈希表;

import java.util.*;
import java.util.TreeMap;

/**
 * @author:fish
 * @date: 2023/2/5-14:51
 * @content: 哈希表（k,v）表：
 * 1，无论曾删改查 时间复杂度都是O（1），但要比数组的查找慢；
 * 2，基础类型（Integer，String,Char,等）按值查询，只看值，不看地址
 * 3，非基础类型（自定义类型）按引用传递，看地址。
 * 4，基础类型所占的字节数 map< Integer,String >,为其Integer和String的内容所占字节和
 * 5，自定义类型字节数 map< Node,Node >,其一个Node为一个地址，所占8字节。
 * 有序表 TreeMap
 * 相较于哈希表新增如下功能：
 * 0，时间复杂度O（logN），且暂不支持自定义类型，除非类型已排序。
 * 1，查找key最小 -> treeMap.firstKey()
 * 2，查找key最大 -> treeMap.lastKey()
 * 3，查找<=num最近的key -> treeMap.floorKey(num)
 * 4，查找>=num最近的key -> treeMap.ceilingKey(num)
 */
public class code4_hashMapTreeMap {
    public static class Node {
        public int value;

        public Node(int v) {
            value = v;
        }
    }

    public static void main(String[] args) {
        HashMap<Integer, String> map1 = new HashMap<>();
        Integer k = 11;
        Integer kk = 11;
        map1.put(k, "fisher");
        System.out.println(k == kk); //比较的是地址
        System.out.println(map1.containsKey(k)); //true
        System.out.println(map1.containsKey(kk)); //true

        HashMap<Node, Integer> map2 = new HashMap<>();
        Node node1 = new Node(1);
        Node node2 = new Node(1);
        map2.put(node1, 111);
        System.out.println(map2.containsKey(node1)); //true 按引用传递，比较地址
        System.out.println(map2.containsKey(node2)); //false

        System.out.println();

        TreeMap<Integer, String> treeMap = new TreeMap<>();
        treeMap.put(11, "haha");
        treeMap.put(14, "haha");
        treeMap.put(1, "haha");
        treeMap.put(21, "haha");
        treeMap.put(13, "haha");
        treeMap.put(2, "haha");

        System.out.println(treeMap.containsKey(11)); //true
        treeMap.put(11, "fish");
        System.out.println(treeMap.containsKey(11) + " value: " + treeMap.get(11)); //true
        treeMap.remove(11);
        System.out.println(treeMap.containsKey(11)); //false

        System.out.println("key最小值：" + treeMap.firstKey());
        System.out.println("key最大值：" + treeMap.lastKey());
        System.out.println("离<=15最近的数" + treeMap.floorKey(15));
        System.out.println("离>=5最近的数" + treeMap.ceilingKey(5));


    }
}
