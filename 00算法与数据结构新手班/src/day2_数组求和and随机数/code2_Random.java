package day2_数组求和and随机数;

/**
 * @author:fish
 * @date: 2023/2/3-11:19
 * @content:
 * Math.pow(x, 2) 求x的平方
 *
 * Math.random() -> double -> [0，1)
 * Math.random()*n -> double -> [0，n)
 * (int)(Math.random()*k) -> int -> [0，k-1]  //k为整数
 *
 * 0,1等概率法
 * ① 选定数组（越简单越好）
 * ②确定0，1的概率
 * ③将所求的的范围 改成[0,x],其中x为二进制位所在的长度，如17~56.则范围改为[0,63]
 * ④将所求的的范围 改成[0,范围差]
 * ⑤ 上一步加起始值
 */
public class code2_Random {

    //任意x属于[0，1)，[0，x)出现的概率由x变为x的平方
    public static double xToXPower2() {
        return Math.max(Math.random(), Math.random());
    }

    public static double xToXPower3() {
        return Math.max(Math.random(), Math.max(Math.random(), Math.random()));
    }


    //要求1~5随机到1~7随机的等概率。
    //返回[1,5]期间的整数,固定的只为得到等概率0和1。
    public static int f1(){
        return (int)(Math.random()*5+1);
    }
    //用f1返回概率0，1 不出现3
    public static int f2(){
        int ans=0;
        do {
            ans=f1();
        }while (ans==3);
        return ans<3 ? 0:1;
    }
    //得到000~111,等概率0~7的概率返回一个
    public static int f3(){
        return (f2()<<2)+(f2()<<1)+f2();
    }
    //得到0~6的概率
    public static int f4(){
        int ans=0;
        do {
            ans=f3();
        }while (ans==7);
        return ans;
    }

    //最终目标：得到1~7的概率
    public static int g(){
        return f4()+1;
    }

    //举一反三：得到17~56的等概率
    public static int h1(){
        return (int)(Math.random()*4+1); //返回[1,4]
    }
    public static int h2(){
        int ans=h1();
        return ans<3 ?0:1;
    }
    //求得000000~111111 0~63的等概率
    public static int h3(){
        return (h2()<<5)+(h2()<<4)+(h2()<<3)+(h2()<<2)+(h2()<<1)+h2();
    }
    public static int h4(){
        int ans=0;
        do {
            ans=h3();
        }while (ans>39);
        return ans;
    }
    public static int hh(){
        return h4()+17;
    }

    //01不等概率到01等概率
    public static int x(){
        return Math.random()<0.84 ? 0:1;
    }
    public static int y(){
        int ans=0;
        do {
            ans=x();
        }while (ans==x());
        return ans;
    }

    public static void main(String[] args) {
        int count = 0;
        int testTime = 1000;
        double x = 0.3;
        for (int i = 0; i < testTime; i++) {
            if (xToXPower2() < x)
                count++;
        }
        System.out.println((double) count / (double) testTime);
        System.out.println(Math.pow(x, 2));
        System.out.println("#############");

        count = 0;
        for (int i = 0; i < testTime; i++) {
            if (xToXPower3() < x)
                count++;
        }
        System.out.println((double) count / (double) testTime);
        System.out.println(Math.pow(x, 3));
        System.out.println("#############");

        count=0;
        for (int i = 0; i < testTime; i++) {
            if (f2()==0)
                count++;
        }
        System.out.println("1,2出现的概率为："+(double) count / (double) testTime);
        System.out.println("#############");

        int[] counts = new int[8];
        for (int i = 0; i < testTime; i++) {
            int num=g();
            counts[num]++;
        }
        for (int i = 0; i < 8; i++) {
            System.out.println(i+"出现了"+counts[i]+"次,概率为："+(double) counts[i] / (double) testTime);
        }
        System.out.println("#############");

        int[]countss =new int[57];
        for (int i = 0; i < testTime; i++) {
            int numm=hh();
            countss[numm]++;
        }
        for (int i = 0; i < 57; i++) {
            System.out.println(i+"出现了"+countss[i]+"次,概率为："+(double) countss[i] / (double) testTime);
        }
        System.out.println("#############");

        count=0;
        for (int i = 0; i < testTime; i++) {
            if (y()==0)
                count++;
        }
        System.out.println("0出现的概率为："+(double) count / (double) testTime);
        System.out.println("#############");

    }
}
