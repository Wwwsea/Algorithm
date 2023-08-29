package day01_滑动窗口And单调栈;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author:fish
 * @date: 2023/4/10-16:17
 * @content: 单调栈,取得每个位置的左小坐标和右小坐标.
 * 如何取list的最后一个值？: list.get(list.size() - 1)
 */
public class code03_MonotonousStack {
    //数组中无重复值
    public static int[][] getNearLessNoRepeat(int[] arr) {
        //res[i][0]存放  arr中第i个位置的左侧较小值
        int[][] res = new int[arr.length][2];
        Stack<Integer> stack = new Stack<>();

        //进来值比栈顶值大 则进入栈;
        //进来值比栈顶值小 栈顶值弹出,进来值为弹出值的右小,新栈顶值为左小
        //遍历完所有数组 栈不为空。栈顶弹出，弹出值的右小为无，栈若不为空，左小为新栈顶
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[i] < arr[stack.peek()]) {
                int popIndex = stack.pop();
                int leftLessIndex = stack.isEmpty() ? -1 : stack.peek();
                res[popIndex][0] = leftLessIndex;
                res[popIndex][1] = i;
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            int popIndex = stack.pop();
            int leftLessIndex = stack.isEmpty() ? -1 : stack.peek();
            res[popIndex][0] = leftLessIndex;
            res[popIndex][1] = -1;
        }
        return res;
    }

    //可能含有重复值 stack每层存放的为值相等的下标list集合 底-->高   小-->大
    public static int[][] getNearLess(int[] arr){
        int[][] res=new int[arr.length][2];
        Stack<List<Integer>> stack=new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[i]<arr[stack.peek().get(0)]){
                List<Integer> popList = stack.pop();
                //取新栈顶所在的list集合里最后加入(最后一个位置)的值
                int leftLessIndex=stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size()-1);
                for (Integer popi : popList) {
                    res[popi][0]=leftLessIndex;
                    res[popi][1]=i;
                }
            }

            if (!stack.isEmpty() && arr[i]==arr[stack.peek().get(0)]){
                stack.peek().add(Integer.valueOf(i));
            }else {
                ArrayList<Integer> list=new ArrayList<>();
                list.add(i);
                stack.push(list);
            }
        }
        //数组循环结束 单调栈仍不未空
        while (!stack.isEmpty()){
            List<Integer> popIndexs = stack.pop();
            int leftLessIndex=stack.isEmpty()?-1 : stack.peek().get(stack.peek().size()-1);
            for (Integer popi : popIndexs) {
                res[popi][0]=leftLessIndex;
                res[popi][1]=-1;
            }
        }
        return res;
    }
    //for test 生成无重复值的数组
    public static int[] getRandomArrayNoRepeat(int size){
        int[] arr = new int[(int) (Math.random()*size)+1];
        for (int i = 0; i < arr.length; i++) {
            arr[i]=i;
        }
        for (int i = 0; i < arr.length; i++) {
            int swapIndex=(int)(Math.random()* arr.length);
            int temp=arr[swapIndex];
            arr[swapIndex]=arr[i];
            arr[i]=temp;
        }
        return arr;
    }
    public static int[] getRandomArray(int size,int max){
        int[] arr=new int[(int) Math.random()*size+1];
        for (int i = 0; i <arr.length ; i++) {
            arr[i]=(int)( Math.random()*max+1) - (int)( Math.random()*max);
        }
        return arr;
    }
    //for test
    public static void main(String[] args) {
        int[] arr=getRandomArrayNoRepeat(86);
        for (int i : arr) {
            System.out.print(i+" ");
        }
        int[][] noR=getNearLessNoRepeat(arr);
        int[][] re=getNearLess(arr);
        for (int i = 0; i < noR.length; i++) {
            for (int j = 0; j < noR[0].length; j++) {
                if (noR[i][j]!=re[i][j]) {
                    System.out.println("Oops~");
                }
            }
        }
        System.out.println("Success");
    }
}
