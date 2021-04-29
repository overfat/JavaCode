package com.hfx.动态规划;

/**
 * 给定数组arr，arr中所有的值都为正数且不重复。每个值代表一种面值的货币，每中面值的货币可以使用任意张，再给定一个正数aim代表要找
 * 的钱数，求换钱有多少种方法。
 */
public class 换钱方法数 {
    public static void main(String[] args) {
        int[] arr = new int[]{5,10,25,1};
        int res = coins1(arr, 15);
        System.out.println(res);
    }

    public static int coins1(int[] arr, int aim){
        if(arr == null || arr.length == 0 || aim <0)
            return 0;
        return process1(arr,0,aim);
    }

    private static int process1(int[] arr, int index, int aim) {
        int res = 0;
        if(index == arr.length)
            res = aim == 0 ? 1 : 0;
        else{
            for(int i = 0; arr[index]*i <= aim; i++)
                res += process1(arr,index+1, aim - arr[index]*i);
        }
        return res;
    }
    /**
     * 动态规划的方法，构建一个二维数组dp[N][aim+1]，其中dp[i][j]表示使用arr[0...i]的钱组成aim的最小方法数
     */

    public static int coins2(int[] arr, int aim){
        if(arr == null || arr.length == 0 || aim < 0)
            return 0;
        // 对第一行和第一列进行初始化
        int N = arr.length;
        int[][] dp = new int[N][aim+1];
        //第一列初始化，当钱数为0时，构成的方法只有一种
        for(int i = 0; i < N; i++)
            dp[i][0] = 1;
        //第一行初始化
        for(int i = 0; arr[0]*i <= aim; i++)
            dp[0][arr[0]*i] = 1;
        // 对于dp[i][j]来说，如果构成j不使用arr[]
        for(int i = 1; i < N; i++)
            for(int j = 1; j <= aim; j++){
                dp[i][j] = dp[i-1][j];
                dp[i][j] += j - arr[i] >= 0 ? dp[i][j - arr[i]] : 0;
            }

        return dp[arr.length - 1][aim];
    }

}
