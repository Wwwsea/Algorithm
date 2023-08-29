/**
 * @author:fish
 * @date: 2023/8/10-17:09
 * @content:
 */
public class SpellLetter {
    public static String correctSpelling(String word) {
        StringBuilder correctedWord = new StringBuilder();
        int length = word.length();

        for (int i = 0; i < length; i++) {
            char currentChar = word.charAt(i);

            // 规则1：三个同样的字母连在一起，去掉一个
            if (i < length - 2 && currentChar == word.charAt(i + 1) && currentChar == word.charAt(i + 2)) {
                continue;
            }

            // 规则2：两对一样的字母连在一起，去掉第二对的一个字母
            if (i < length - 3 && currentChar == word.charAt(i + 1) && word.charAt(i + 2) == word.charAt(i + 3)) {
                continue;
            }

            correctedWord.append(currentChar);
        }

        return correctedWord.toString();
    }

    public static void main(String[] args) {
        String word = "woooooooooooooooow";
        String correctedWord = correctSpelling(word);
        System.out.println(correctedWord); // 输出: wow
    }
}
