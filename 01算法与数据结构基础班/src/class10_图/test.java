package class10_图;

import java.security.interfaces.EdECKey;
import java.util.*;

/**
 * @author:fish
 * @date: 2023/8/31-9:49
 * @content:
 */
public class test {
    // 宽度优先遍历
    public static void bfs(Node node){
        if (node == null){
            return;
        }
        PriorityQueue<Node> queue = new PriorityQueue<>();
        HashSet<Node> set = new HashSet<>();
        queue.add(node);
        set.add(node);
        while (!queue.isEmpty()){
            Node cur = queue.poll();
            for (Node next : cur.nexts) {
                if (!set.contains(next)){
                    queue.add(next);
                    set.add(next);
                }
            }
        }
    }
    // 深度优先遍历
    public static void dfs(Node node){
        if (node == null){
            return;
        }
        Stack<Node> stack = new Stack<>();
        HashSet<Node> set = new HashSet<>();
        stack.add(node);
        set.add(node);
        System.out.println(node.value);
        while (!stack.isEmpty()){
            Node cur = stack.pop();
            for (Node next : cur.nexts) {
                if (!set.contains(next)){
                    stack.push(cur);
                    stack.push(next);
                    set.add(next);
                    break;
                }
            }
        }
    }

    // 拓扑排序
    public static List<Node> top(Graph graph){
        if (graph == null){
            return null;
        }
        List<Node> res = new ArrayList<>();
        HashMap<Node,Integer> inMap = new HashMap<>();
        PriorityQueue<Node> zeroQueue = new PriorityQueue<>();
        for (Node node : graph.nodes.values()) {
            inMap.put(node, node.in);
            if (node.in == 0) {
                zeroQueue.add(node);
            }
        }

        while (!zeroQueue.isEmpty()){
            Node n = zeroQueue.poll();
            res.add(n);
            for (Node next : n.nexts) {

                inMap.put(next,inMap.get(next)-1);
                if (inMap.get(next) == 0){
                    zeroQueue.add(next);
                }
            }
        }

        return res;
    }

    public class UnionFind{
        HashMap<Node,Node> fatherMap; // 前节点 向上能找的父亲节点为后节点
        HashMap<Node,Integer> sizeMap; // 以当前节点为头节点的集团 里面的节点数

        UnionFind(){
            fatherMap = new HashMap<>();
            sizeMap = new HashMap<>();
        }

        public void makeSets(Collection<Node> nodes){
            fatherMap.clear();
            sizeMap.clear();
            for (Node node : nodes) {
                fatherMap.put(node,node);
                sizeMap.put(node,1);
            }
        }
        public Node findFather(Node node){
            Stack<Node> path = new Stack<>();
            while (node != fatherMap.get(node)){
                path.push(node);
                node = fatherMap.get(node);
            }

            while (!path.isEmpty()){
                fatherMap.put(path.pop(),node);
            }
            return node;
        }
        public boolean isSameSet(Node n1, Node n2){return fatherMap.get(n1) == fatherMap.get(n2);}

        public void union(Node n1, Node n2){
            if (n1 == null || n2 == null)
                return;
            Node f1 = fatherMap.get(n1);
            Node f2 = fatherMap.get(n2);

            if (f1 != f2){
                Node fa = sizeMap.get(f1) <= sizeMap.get(f2) ? f2 : f1;
                Node son = fa == f2 ? f1 : f2;
                fatherMap.put(son,fa);
                sizeMap.put(fa,sizeMap.get(fa)+sizeMap.get(son));
                sizeMap.remove(son);{
            }
        }
    }
    }
    public static class EdgeCompare implements Comparator<Edge>{

        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }
    }
    public Set<Edge> Kruskal(Graph graph){
        UnionFind unionFind = new UnionFind();
        unionFind.makeSets(graph.nodes.values());
        Set<Edge> res = new HashSet<>();
        PriorityQueue<Edge> queue = new PriorityQueue<>(new EdgeCompare());
        for (Edge edge : graph.edges) {
            queue.add(edge);
        }

        while (!queue.isEmpty()){
            Edge edge = queue.poll();
            if (!unionFind.isSameSet(edge.from, edge.to)){
                unionFind.union(edge.from, edge.to);
                res.add(edge);
            }
        }
        return res;
    }
    public Set<Edge> Prim(Graph graph){
        Set<Edge> res = new HashSet<>();
        HashSet<Node> nodeSet = new HashSet<>();
        PriorityQueue<Edge> queue = new PriorityQueue( new EdgeCompare());
        for (Node node : graph.nodes.values()) {
            // node作为随机开始点
            if (!nodeSet.contains(node)){
                nodeSet.add(node);
                for (Edge edge : node.edges) {
                    queue.add(edge);
                }
                while (!queue.isEmpty()){
                    Edge e = queue.poll();
                    Node n = e.to;
                    if (!nodeSet.contains(n)){
                        nodeSet.add(n);
                        res.add(e);
                        for (Edge edge : n.edges) {
                            if (!res.contains(edge)){
                                queue.add(edge);
                            }
                        }
                    }
                }

            }

            break;
        }


        return res;
    }
}
