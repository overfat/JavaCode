package 每日一题;

/**
 * 数组异或操作
 * 给你两个整数，n 和 start 。
 *
 * 数组 nums 定义为：nums[i] = start + 2*i（下标从 0 开始）且 n == nums.length 。
 *
 * 请返回 nums 中所有元素按位异或（XOR）后得到的结果
 */

public class P486 {
    public static void main(String[] args){
        int result = xopOperation(4,3);
        System.out.println(result);
    }
    // 异或运算符 ^
    public static int xopOperation(int n, int start){
        if(n == 0)
            return 0;
        if(n == 1)
            return start;
        int result = start;
        int newValue = 0;
        for(int i = 1; i < n; i++)
        {
            newValue = start + 2*i;
            result = result ^ newValue;
        }

        return result;


    }
}
