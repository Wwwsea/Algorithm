package day10_矩阵处理技巧;

/**
 * @author:fish
 * @date: 2023/5/3-22:34
 * @content: 转圈圈打印矩阵
 */
public class code02_SpiralOrderPrintMatrix {
    public static void spiralOrderPrint(int[][] m){
        int Ar = 0;
        int Ac = 0;
        int Bc = m.length - 1;
        int Br = m[0].length - 1;
        while ( Ar <=Br && Ac <= Bc){
            printEdge(m,Ar++,Ac++,Br--,Bc--);
        }
    }
    public static void printEdge(int[][] m, int Ar,int Ac,int Br,int Bc){
        if (Ar == Br){
            for (int i = Ac; i <= Bc ; i++) {
                System.out.print(m[Ar][i]+" ");
            }
        }else if (Ac == Bc){
            for (int i = Ar; i <= Br; i++) {
                System.out.print(m[i][Ac]);
            }
        }else {
            int curR = Ar;
            int curC = Ac;
            while (curC != Bc){
                System.out.print(m[curR][curC++]+" ");
            }
            while (curR !=Br){
                System.out.print(m[curR++][curC]+" ");
            }
            while (curC != Ac) {
                System.out.print(m[curR][curC--]+" ");
            }
            while (curR != Ar){
                System.out.print(m[curR--][curC]+" ");
            }
        }
    }
    public static void main(String[] args) {
        int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 },
                { 13, 14, 15, 16 } };
        spiralOrderPrint(matrix);
    }
}
