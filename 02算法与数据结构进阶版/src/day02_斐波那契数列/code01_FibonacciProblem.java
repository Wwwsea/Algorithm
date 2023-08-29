package day02_斐波那契数列;

import jdk.swing.interop.SwingInterOpUtils;

/**
 * @author:fish
 * @date: 2023/4/13-10:17
 * @content:
 */
public class code01_FibonacciProblem {
    //基础版本 O(N)
    public static int f1(int n) {
        if (n == 1 || n == 2)
            return 1;
        if (n < 1)
            return 0;
        return f1(n - 1) + f1(n - 2);
    }

    //线性代数版 O(logN)
    public static int f2(int n) {
        if (n == 1 || n == 2)
            return 1;
        if (n < 1)
            return 0;

        int[][] base = {{1, 1}, {1, 0}};
        int[][] res=matrixPower(base,n-2);
        return res[0][0]+res[1][0];
    }
    public static int[][] matrixPower(int[][] m,int p){
        int[][] res=new int[m.length][m[0].length];
        for (int i = 0; i < res.length; i++) {
            res[i][i]=1;
        }
        int[][] tmp=m; //矩阵的一次方
        for (;p!=0;p>>=1){
            //最右侧为1，才将当前次方值乘进结果中
            if ((p&1L)!=0) {
                res = muliMatrix(res, tmp);
            }
            //base的p次方,下一次为2p次方
            tmp=muliMatrix(tmp,tmp);
        }
        return res;
    }
    // 返回矩阵的乘法
    public static int[][] muliMatrix(int[][] m1,int[][]m2){
        int[][] res=new int[m1.length][m2[0].length];
        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m2[0].length; j++) {
                for (int k = 0; k < m2.length; k++) {
                    res[i][j] += m1[i][k]*m2[k][j];
                }
            }
        }
        return res;
    }

    // 卖台阶问题，起初一台阶，有一种卖法；两台阶有两种卖法；三台阶 f（2）+f（1）
    // | Fn Fn-1 | == | F2 F1 | * | 二阶矩阵 |的n-2次方
    public static int s3(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return n;
        }
        int[][] base = { { 1, 1 }, { 1, 0 } };
        int[][] res = matrixPower(base, n - 2);
        // |2，1|*|base矩阵|的(n-2)次方
        return 2 * res[0][0] + res[1][0];
    }

    // 农场奶牛问题,起初一只母牛，每年产一只，新奶牛三年后才可产牛，假设奶牛都不死，问n年后农场有多少只奶牛
    public static int c3(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2 || n == 3) {
            return n;
        }
        int[][] base = {
                { 1, 1, 0 },
                { 0, 0, 1 },
                { 1, 0, 0 } };
        int[][] res = matrixPower(base, n - 3);
        // |F3 F2 F1| * |base矩阵|的n-3次方
        return 3 * res[0][0] + 2 * res[1][0] + res[2][0];
    }

    public static void main(String[] args) {
        int n=25;
        System.out.println(f1(n));
        System.out.println(f2(n));
        System.out.println(s3(n));
        System.out.println(c3(n));

    }
}
