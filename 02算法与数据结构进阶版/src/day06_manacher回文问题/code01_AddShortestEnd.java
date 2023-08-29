package day06_manacher回文问题;

/**
 * @author:fish
 * @date: 2023/4/21-9:19
 * @content: manacher算法的用法:
 * 给定字符串 要求从右侧加入最短的字串，使其变成回文
 */
public class code01_AddShortestEnd {
    public static String shortestEnd(String s){
        if (s==null || s.length()==0)
            return null;
        char[] str= getManacherString(s);
        int[] pArr=new int[str.length];
        int C = -1;
        int R = -1;
        int maxContainEnd=Integer.MIN_VALUE;

        for (int i = 0; i < str.length; i++) {
            pArr[i] = R>i ? Math.min(pArr[2*C-i],R-i) : 1;
            while (i+pArr[i]< str.length && i-pArr[i]> -1){
                if (str[i+pArr[i]] == str[i-pArr[i]]){
                    pArr[i]++;
                }else break;
            }
            if (i+pArr[i]>R){
                R = i+pArr[i];
                C = i;
            }
            if (R == str.length) {
                maxContainEnd = pArr[i];
                break;
            }
        }
        //补齐
        char[] res=new char[s.length() - maxContainEnd + 1];
        for (int i = 0; i < res.length; i++) {
            // 逆序
            res[res.length - 1 - i] = str[i * 2 + 1];
        }
        return String.valueOf(res);
    }
    public static char[] getManacherString(String s){
        char[] str=s.toCharArray();
        int index=0;
        char[] res=new char[str.length*2+1];
        for (int i = 0; i < res.length; i++) {
            res[i]= (i&1)==0 ? '#' : str[index++];
        }
        return res;
    }

    public static void main(String[] args) {
        String str1 = "abcd123321";
        System.out.println(shortestEnd(str1));
    }

}
