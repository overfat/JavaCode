package com.hfx.动态规划;

/**
 * 给定数组arr，arr中所有的值都为正数且不重复。每个值代表一种面值的货币，每中面值的货币可以使用任意张。
 * 在给定一个正数aim代表要找的钱数，求组成aim的最少货币币数
 */
public class 换钱的最少货币数 {
    public static void main(String[] args) {
        int[] arr = new int[]{2,3,5};
        int target = 20;
        int res = minCons(arr, target);
        System.out.println(res);
    }

    public static int minCons(int[] arr, int aim){
        // 首先进行判断
        if(arr == null || arr.length == 0 || aim < 0)
            return 0;
        int N = arr.length;
        int max = Integer.MAX_VALUE;
        // 新建一个数组
        int[][] dp = new int[N][aim+1];
        dp[0][0] = 0;
        // 对第一行进行初始化
        for(int i = 1; i < aim+1; i++){
            dp[0][i] = max;
            if(i - arr[0] >= 0 && dp[0][i - arr[0]] != max)
                dp[0][i] = dp[0][i - arr[0]] + 1;
        }
        // dp数组中其余的值按照从左到右，从上到下来处理
        for(int i = 1; i < N; i++){
            for(int j = 1; j <= aim; j++){
                int left = max;
                if(j - arr[i] >=0 && dp[i][j - arr[i]] != max){
                    left = dp[i][j - arr[i]] + 1;
                }
                dp[i][j] = Math.min(left, dp[i-1][j]);
            }
        }
        return dp[N-1][aim];
    }
    // 压缩数组的情况
    public static int minCons2(int[] arr, int aim){
        // 压缩数组，建立一个大小为aim+1的一维数组
        if(arr == null || arr.length == 0 || aim <=0)
            return 0;
        //新建数组
        int[] dp = new int[aim+1];
        int max = Integer.MAX_VALUE;
        // 先对数组进行初始化
        for(int i = 1; i <= aim; i++){
            dp[i] = max;
            if(i - arr[0] >= 0 && dp[i - arr[0]] != max){
                dp[i] = dp[i - arr[0]] + 1;
            }
        }
        // 后面的数值按照从左到右，从上到下的方式去求
        for(int i = 1; i < arr.length; i++){
            for(int j = 1; j <= aim; j++){
                int left = max;
                if(j - arr[i] >= 0 && dp[j - arr[i]] != max){
                    left = dp[j - arr[i]] + 1;
                }
                dp[j] = Math.min(left, dp[j]);
            }
        }
        return dp[aim];
    }
}
