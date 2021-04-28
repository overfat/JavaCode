package 位运算;

/**
 * 请实现一个函数，输入一个整数（以二进制串形式），输出该数二进制表示中 1 的个数。
 * 例如，把 9表示成二进制是 1001，有 2 位是 1。因此，如果输入 9，则该函数输出 2。
 */
public class P15 {
    public static void main(String[] args) {
        int result = hammingWeight2(00000000000000000000000000001011);
        System.out.println(result);
    }

    public static int hammingWeight(int n) {
        // 方法一:利用与运算，当n的最右边为1时，n&1为1，否则为0
        int res = 0;
        while(n!=0){
            res += n&1;
            n = n >>> 1;  // 比较完最右边的那一位时，需要把n向右移动一位
        }

        return res;
    }
    public static int hammingWeight2(int n) {
        // 方法二:利用与运算，利用n&n-1进行运算
        int res = 0;
        while(n!=0){
            n = n&n-1;
            res++;  // 比较完最右边的那一位时，需要把n向右移动一位
        }

        return res;
    }


}
