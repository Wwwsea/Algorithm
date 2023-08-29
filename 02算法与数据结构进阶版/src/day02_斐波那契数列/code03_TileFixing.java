package day02_斐波那契数列;

/**
 * @author:fish
 * @date: 2023/4/13-21:26
 * @content: 贴瓷砖问题。区域面积2*N
 * 瓷砖 1*2.求贴完整面墙有多少种贴法
 */
public class code03_TileFixing {
    public static int getNums(int N){
        if (N<1)
            return 0;
        if (N==1||N==2)
            return N;
        return getNums(N-2)+getNums(N-1);
    }
    public static int getNums2(int n){
        if (n<1)
            return 0;
        if (n==1 || n==2)
            return n;

        int[][] base ={{1,1},{1,0}};
        int[][] res=matrixPower(base,n-2);
        return 2*res[0][0] +res[1][0];
    }
    public static int[][] matrixPower(int[][] m, int p){
        int[][] res= new int[m.length][m[0].length];
        for (int i = 0; i < res.length; i++) {
            res[i][i]=1;
        }
        int[][] tmp=m;
        for (;p!=0;p>>=1) {
            if ((p&1L)!=0)
                res=multiMatrix(res,tmp);
            tmp=multiMatrix(tmp,tmp);
        }
        return res;
    }
    public static int[][] multiMatrix(int[][] m1, int[][] m2){
        int[][] res=new int[m1.length][m2[0].length];
        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m2[0].length; j++) {
                for (int k = 0; k < m2.length; k++) {
                    res[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(getNums(5));
        System.out.println(getNums2(5));
    }

}
