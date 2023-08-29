package day04_KMP及应用;

import java.util.ArrayList;

/**
 * @author:fish
 * @date: 2023/4/18-20:01
 * @content: 两棵树，其中一颗是另一颗的子树，要求结构和值一致
 */
public class code03_IsEqualTree {
    public static class Node{
        public Node left;
        public Node right;
        public int value;
        public Node(int v){
            value=v;
        }
    }
    // big做头节点的树，其中是否有某棵子树的结构，是和small为头的树，完全一样的
    public static boolean containsTree1(Node big, Node small){
        if (small==null)
            return true;
        if (big==null)
            return false;
        if (isSameValueStructure(big,small))
            return true;
        return containsTree1(big.left, small) || containsTree1(big.right, small);

    }
    // head1为头的树，是否在结构对应上，完全和head2一样
    public static boolean isSameValueStructure(Node head1, Node head2){
        if (head1==null ^ head2==null)
            return false;
        if (head1==null && head2==null)
            return true;
        if (head1.value != head2.value)
            return false;
        return isSameValueStructure(head1.left,head2.left)
                && isSameValueStructure(head1.right,head2.right);
    }

    public static boolean containsTree2(Node big, Node small){
        if (small==null)
            return true;
        if (big==null)
            return false;
        ArrayList<String> b=preSerial(big);
        ArrayList<String> s=preSerial(small);
        String[] str =new String[b.size()];
        String[] match = new String[s.size()];

        for (int i = 0; i <str.length; i++) {
            str[i]=b.get(i);
        }
        for (int i = 0; i < match.length; i++) {
            match[i]=s.get(i);
        }
        return getIndexOf(str,match) != -1;
    }

    //KMP Algorithm
    public static int getIndexOf(String[] str,String[] match){
        if (str==null || match==null || str.length< match.length){
            return -1;
        }
        int x=0;
        int y=0;
        int[] next=getNextArray(match);
        while (x< str.length && y< match.length){
            if (isEqual(str[x],match[y])){
                x++;
                y++;
            }else if (next[y]!=-1){
                y=next[y];
            }else {
                x++;
            }
        }
        return y== match.length ? x-y : -1;
    }
    public static int[] getNextArray(String[] match){
        if (match.length==1)
            return new int[] {-1};
        int[] next=new int[match.length];
        next[0]=-1;
        next[1]=0;
        int cn=0;
        int i=2;
        while (i< match.length){
            if (isEqual(match[cn],match[i-1])){
                next[i++]=++cn;
            }else if (cn>0){
                cn=next[cn];
            }else {
                next[i++]=0;
            }
        }
        return next;
    }
    //判断两字符串是否相等
    public static boolean isEqual(String a, String b){
        if (a==null && b==null)
            return true;
        else {
            if (a==null ^ b==null)
                return false;
            else {
                return a.equals(b);
            }
        }
    }

    //获得先序的结构化序列，空位置拿null补齐
    public static ArrayList<String> preSerial(Node head){
        ArrayList<String> ans=new ArrayList<>();
        pres(head,ans);
        return ans;
    }
    public static void pres(Node head,ArrayList<String> ans){
        if (head==null)
            ans.add(null);
        else {
            ans.add(String.valueOf(head.value));
            pres(head.left,ans);
            pres(head.right,ans);
        }
    }


    // for test
    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }
    public static void main(String[] args) {
        int bigTreeLevel = 7;
        int smallTreeLevel = 4;
        int nodeMaxValue = 5;
        int testTimes = 100000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            Node big = generateRandomBST(bigTreeLevel, nodeMaxValue);
            Node small = generateRandomBST(smallTreeLevel, nodeMaxValue);
            boolean ans1 = containsTree1(big, small);
            boolean ans2 = containsTree2(big, small);
            if (ans1 != ans2) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish!");

    }


}
