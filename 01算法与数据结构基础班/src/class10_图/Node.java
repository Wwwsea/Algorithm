package class10_图;

import java.util.ArrayList;

// 点结构的描述  A  0
// 或者L: Map<String,Map<String.Integer>>
public class Node {
	public int value;
	public int in;   // 入度
	public int out;  // 出度
	public ArrayList<Node> nexts;  // 可以指出去的节点
	public ArrayList<Edge> edges;

	public Node(int value) {
		this.value = value;
		in = 0;
		out = 0;
		nexts = new ArrayList<>();
		edges = new ArrayList<>();
	}
}
