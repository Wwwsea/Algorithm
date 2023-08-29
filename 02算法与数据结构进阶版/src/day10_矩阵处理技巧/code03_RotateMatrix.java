package day10_矩阵处理技巧;

/**
 * @author:fish
 * @date: 2023/5/3-22:53
 * @content: 原地旋转正方形矩阵
 */
public class code03_RotateMatrix {
    public static void rotate(int[][] matrix) {
        int a = 0;
        int b = 0;
        int c = matrix.length - 1;
        int d = matrix[0].length - 1;
        while (a<c){
            rotateEdge(matrix,a++,b++,c--,d--);
        }
    }
    public static void rotateEdge(int[][] m,int a,int b,int c,int d) {
        int temp= 0;
        // 4*4 可分为3组
        // 5*5 可分为4组
        // i*i 可分为i—1组
        for (int i = 0; i < d - b; i++) {
            temp= m[a][b+i];
            m[a][b+i] = m[a+i][b];
            m[a+i][b] = m[c][d-i];
            m[c][d-i] = m[a+i][d];
            m[a+i][d] = temp;
        }
    }
    // for test
    public static void printMatrix(int[][] m){
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                System.out.print(m[i][j]+" ");
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13, 14, 15, 16 } };
        printMatrix(matrix);
        rotate(matrix);
        System.out.println("=========");
        printMatrix(matrix);

    }
}
