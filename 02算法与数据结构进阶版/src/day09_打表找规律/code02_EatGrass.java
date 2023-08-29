package day09_打表找规律;

/**
 * @author:fish
 * @date: 2023/4/28-11:27
 * @content: 两头牛轮流吃草 每次只会吃4的n次方 数量的草
 * 先手后手都绝顶聪明
 * 返回最后 的赢家
 */
public class code02_EatGrass {
    public static String winner(int n){
        // 0  1  2  3 4
        // 后 先 后 先 先
        if (n<5)
            return (n==0 || n == 2) ? "后手" : "先手";
        int base =1; //先手准备吃的草个数
        // 当前是先手在选
        while (base<=n){
            // 当前一共n份草，先手吃掉的是base份，n - base 是留给后手的草
            // 母过程 先手 在子过程里是 后手
            if (winner(n-base).equals("后手"))
                return "先手";
            if ((n/4)<base) //防止溢出
                break;
            base *=4;
        }
        return "后手";
    }
    // 后 先 后 先 先
    public static String winnerAwesome(int n){
        if (n % 5 == 0 || n % 5==2)
            return "后手";
        else return "先手";
    }

    public static void main(String[] args) {
        for (int i = 0; i < 55; i++) {
            System.out.println(i+" : "+winner(i) + "====" + winnerAwesome(i));
        }
    }
}
