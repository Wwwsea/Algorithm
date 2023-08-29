package day10_矩阵处理技巧;

import java.util.Base64;

/**
 * @author:fish
 * @date: 2023/5/3-21:27
 * @content: Z字形打印矩阵
 */
public class code01_ZigZagPrintMatrix {
    public static void printMatrixZigZag(int[][] m) {
        int Ar = 0;  // 起始在上方的点A(Ar,Ac)
        int Ac = 0;
        int Br = 0;  // 起始在左方的点B(Br,Bc)
        int Bc = 0;
        int endR = m.length - 1;
        int endC = m[0].length - 1;
        boolean fromUp = false;
        while (Ar != endR + 1) {
            printLevel(m,Ar,Ac,Br,Bc,fromUp);
            Ar = Ac == endC ? Ar + 1 : Ar;  // A先向右走，右到不能再右了再向下
            Ac = Ac == endC ? Ac : Ac + 1;
            Bc = Br == endR ? Bc + 1 : Bc;  // B先往下走，不能再向下了再向右走
            Br = Br == endR ? Br : Br + 1;
            fromUp = !fromUp;
        }
        System.out.println();

    }
    public static void printLevel(int[][] m, int Ar, int Ac, int Br, int Bc, boolean fromUp) {
        if (fromUp) {  // ← ↓
            while (Ar != Br + 1) {
                System.out.print(m[Ar++][Ac--] + " ");
            }
        } else {  // → ↑
            while (Br != Ar - 1) {
                System.out.print(m[Br--][Bc++] + " ");
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        printMatrixZigZag(matrix); // result: 1 2 5 10 7 4 4 7 10 12
    }
}
