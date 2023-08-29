package day09_打表找规律;

/**
 * @author:fish
 * @date: 2023/4/25-23:56
 * @content: 现有容量规格为6和8的食品袋, 输入n个苹果
 * <p>
 * 求返回的最小袋子数，要求每个袋子必须装满，不符合要求返回-1
 */
public class code01_AppleMinBags {
    public static int minBags(int apples) {
        if (apples < 1)
            return -1;
        int bag6 = -1;
        int bag8 = apples / 8;
        int rest = apples - 8 * bag8;
        while (bag8 >= 0 && rest < 24) { //24为 6和8的最小公倍数
            int result = restMinBag(rest);
            if (result != -1) {
                bag6 = result;
                break;
            }
            rest = apples - 8 * (--bag8);
        }
        return bag6 == -1 ? -1 : bag6 + bag8;
    }

    // 如果剩余苹果rest可以被装6个苹果的袋子搞定，返回袋子数量
    // 不能搞定返回-1
    public static int restMinBag(int rest) {
        return rest % 6 == 0 ? (rest / 6) : -1;
    }

    // 找规律解法
    public static int minBagsAwesome(int apples) {
        if ((apples & 1) != 0) // 如果是奇数，返回-1
            return -1;
        if (apples < 18)
            return apples == 0 ? -1 : (apples== 6 || apples== 8)? 1 :
                    (apples==12 || apples == 14 || apples == 16)?2 : -1;
        return (apples-18) / 8 +3;
    }

    public static void main(String[] args) {
        for (int apple = 0; apple < 100; apple++) {
            System.out.print(apple + " : " + minBags(apple));
            System.out.print("----"+apple + " : " + minBagsAwesome(apple));
            System.out.println();
        }
    }

}
