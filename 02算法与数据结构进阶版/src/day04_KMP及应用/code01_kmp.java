package day04_KMP及应用;

/**
 * @author:fish
 * @date: 2023/4/18-16:38
 * @content:
 */
public class code01_kmp {
    //O(N)
    public static int getIndexOf(String s, String m) {
        if (s.length() < m.length() || s == null || m == null || m.length() == 0)
            return -1;
        char[] str = s.toCharArray();
        char[] match = m.toCharArray();
        int x = 0; // str中当前对比的下标
        int y = 0; // match中当前对比的下标
        int[] next = getNextArray(match);
        while (x < str.length && y < match.length) {
            if (str[x] == match[y]) {
                x++;
                y++;
            } else if (next[y] == -1) {
                x++;
            } else {
                y = next[y];
            }
        }
        return y == match.length ? x - y : -1;
    }

    //M   O(M)
    public static int[] getNextArray(char[] match) {
        if (match.length == 1)
            return new int[]{-1};
        int[] next = new int[match.length];
        next[0] = -1;
        next[1] = 0;
        int cn = 0; // 相当于next[i]
        int i = 2;    // match到达的当前下标值
        while (i < match.length) {
            if (match[cn] == match[i - 1]) {
                next[i++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[i++] = 0;
            }
        }
        return next;
    }


    // for test 生成随机字符串
    public static String getRandomString(int possibilities, int size) {
        char[] ans = new char[(int) (Math.random() * size) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (char) ((int) (Math.random() * possibilities) + 'a');
        }
        return String.valueOf(ans);
    }
    //for test
    public static String getRandomStrings(int possibilities, int size){
        char[] ans=new char[(int)(Math.random()*size)+1];
        for (int i = 0; i < ans.length; i++) {
            ans[i]=(char) ((int)(Math.random()*possibilities)+'a');
        }
        return ans.toString();
    }

    public static void main(String[] args) {
        int possibilities = 5;
        int strSize = 20;
        int matchSize = 5;
        int testTimes = 5000000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            String str = getRandomString(possibilities, strSize);
            String match = getRandomString(possibilities, matchSize);
            if (getIndexOf(str, match) != str.indexOf(match)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish");
    }
}
