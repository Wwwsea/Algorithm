import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author:fish
 * @date: 2023/4/12-21:16
 * @content: list.subList(fromIndex,toIndex);
 * fromIndex可以取到,toIndex取不到.
 */
public class test {
    public static void main(String[] args) {
        String[] strings = {"foo", "bar", "apple", "banana", "pear"};
        List<String> list = Arrays.asList(strings);
        List<String> sub_list = list.subList(2, 4);

        System.out.println(strings);
        System.out.println(list);
        System.out.println(sub_list);

        //数组模拟栈
        int[] arr = new int[10];
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            arr[i++] = ++index;
            System.out.print(index+" ");
        }
        System.out.println();
        for (int i = arr.length-1; i >= 0; i--) {
            System.out.print( arr[--i]+" ");
        }



    }
}
