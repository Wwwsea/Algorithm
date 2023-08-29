package day04_KMP及应用;

/**
 * @author:fish
 * @date: 2023/4/18-19:40
 * @content: 利用KMP算法 判断两字符串是否互为回转数
 */
public class code02_IsRotation {
    public static boolean isRotation(String str1, String str2) {
        if (str1 == null || str2 == null || str1.length() != str2.length())
            return false;
        String newString = str1+str1;
        return getIndexOf(newString,str2) != -1;

    }

    //KMP Algorithm
    public static int getIndexOf(String s, String m) {
        if (s.length() < m.length())
            return -1;
        char[] str = s.toCharArray();
        char[] match = m.toCharArray();
        int x = 0;
        int y = 0;
        int[] next = getNextArray(match);
        while (x < str.length && y < match.length) {
            if (str[x] == match[y]) {
                x++;
                y++;
            } else if (next[y] != -1) {
                y = next[y];
            } else {
                x++;
            }
        }
        return y == match.length ? x - y : -1;
    }

    public static int[] getNextArray(char[] match) {
        if (match.length == 1)
            return new int[]{-1};
        int[] next = new int[match.length];
        next[0] = -1;
        next[1] = 0;
        int cn = 0;
        int i = 2;
        while (i < match.length) {
            if (match[i - 1] == match[cn]) {
                next[i++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[i++] = 0;
            }
        }
        return next;
    }


    public static void main(String[] args) {
        String str1 = "yunzuocheng";
        String str2 = "zuochengyun";
        System.out.println(isRotation(str1, str2));

    }
}
