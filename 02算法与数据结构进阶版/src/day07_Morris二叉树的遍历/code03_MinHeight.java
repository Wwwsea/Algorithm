package day07_Morris二叉树的遍历;

/**
 * @author:fish
 * @date: 2023/4/25-9:28
 * @content: 找到子树的最小高度
 */
public class code03_MinHeight {
    public static class Node {
        int value;
        Node left;
        Node right;

        Node(int v) {
            value = v;
        }
    }
    public static int minHeight1(Node head){
        if (head==null)
            return 0;
        return p(head);
    }
    public static int p(Node x){
        if (x.left == null && x.right == null)
            return 1;
        // 左右子树起码有一个不为空
        int leftH=Integer.MAX_VALUE;
        if (x.left!=null){
            leftH = p(x.left);
        }
        int rightH=Integer.MAX_VALUE;
        if (x.right != null)
            rightH=p(x.right);

        return Math.min(leftH,rightH)+1;
    }

    //Morris遍历改写
    public static int minHeight2(Node head){
        if (head == null)
            return 0;
        Node cur = head;
        Node mostRight = null;
        int curLevel = 0;
        int minH = Integer.MAX_VALUE;
        while (cur!=null){
            mostRight=cur.left;
            if (mostRight!=null){
                int rightBoard = 1;
                while (mostRight.right != null&& mostRight.right != cur){
                    rightBoard++;
                    mostRight=mostRight.right;
                }
                if (mostRight.right ==null){ //第一次到达
                    curLevel++;
                    mostRight.right=cur;
                    cur=cur.left;
                    continue;
                }else { //第二次到达
                    if (cur.left == null) //判断当前结点是否为叶节点
                        minH = Math.min(curLevel,minH);
                    curLevel -= rightBoard;
                    mostRight.right=null;
                }
            }else { //只到达一次
                curLevel++;
            }
            cur=cur.right;
        }
        //判断最右下一节点的高度
        int finalRight = 1;
        cur=head;
        while (cur.right!=null){
            finalRight++;
            cur=cur.right;
        }
        if (cur.left ==null && cur.right==null)
            minH = Math.min(minH,finalRight);

        return minH;
    }

    //for test
    public static Node generateBST(int maxH,int maxV){
        return generate(1,maxH,maxV);
    }
    public static Node generate(int level, int maxH, int maxV){
        if (level>maxH || Math.random()<0.5)
            return null;
        Node head = new Node((int)(Math.random()*maxV)+1);
        head.left=generate(level+1,maxH,maxV);
        head.right=generate(level+1,maxH,maxV);
        return head;
    }

    public static void main(String[] args) {
        int testTimes=5000;
        int maxLevel=15;
        int maxValue=55;
        System.out.println("test Begin ~");
        for (int i = 0; i < testTimes; i++) {
            Node head = generateBST(maxLevel,maxValue);
            int test1 = minHeight1(head);
            int test2 = minHeight2(head);
            if (test1!=test2)
                System.out.println("Oops~");
        }
        System.out.println("Success !");

    }

}
