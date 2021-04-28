package 动态规划与贪婪;


/**
 * 斐波那契数列第n项
 */
public class P10 {
    public static void main(String[] args){
        int n = 48;
        long result = fib(n);
        System.out.println("结果是：" + result);
    }
    public static int fib(int n) {
        if(n==0 || n == 1)
            return n;
        int pre_2 = 0;
        int pre_1 = 1;
        int result = 0;
        for(int i = 2; i <= n;i++){
            result = (pre_1 + pre_2) % 1000000007;
            pre_2 = pre_1;
            pre_1 = result;
        }
        return result;
    }
}
