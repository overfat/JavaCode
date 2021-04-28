package 位运算;

/**
 * 不使用加减乘除做加法，使用位运算
 */

public class P65 {
    public static void main(String[] args) {
        int a = 20;
        int b = 17;
        int res = add(a,b);
        System.out.println(res);
    }
    public static int add(int a, int b){
        while(b!=0){
            int c = (a&b)<<1;
            a ^= b;
            b = c;
        }
        return a;
    }
}
