package 数学;

/**
 * 实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，xn）。不得使用库函数，同时不需要考虑大数问题。
 */
public class P16 {
    public static void main(String[] args) {
        double res = myPow(2,10);
        System.out.println(res);
    }

    public static double myPow(double x, int n) {
        // 首先需要判断x的值
        if(x == 0){
            return 0;
        }
        long b = n;
        if(b < 0){
            x = 1/x;
            b = -b;
        }
        double res = 1;
        while(b != 0){
            if((b&1) == 1){
                res *= x;
            }
            x *= x;
            b>>>=1;
        }
        return res;
    }
}
