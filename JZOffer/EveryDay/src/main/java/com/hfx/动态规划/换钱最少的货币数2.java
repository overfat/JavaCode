package com.hfx.动态规划;

/**
 * 给定数组arr，arr中所有的值都为正数。每个值仅代表一张钱的面值，在给定一个正数aim代表要找的钱数，求组成aim的最少货币数
 */

public class 换钱最少的货币数2 {
    public static void main(String[] args) {
        int[] arr = new int[]{5,2,5,3};
        int target = 4;
        int count = minCons(arr,target);
        int count2 = minCons2(arr,target);
        System.out.println(count);
        System.out.println(count2);
    }

    public static int minCons(int[] arr, int target) {
        if(arr == null || arr.length == 0){
            return 0;
        }
        int n = arr.length;
        int[][] dp = new int[n][target + 1];
        // 对第一行进行初始化
        int max = Integer.MAX_VALUE;
        if(arr[0] <= target)
            dp[0][arr[0]] = 1;
        for(int i = 1; i <= target; i++ ){
            if(i == arr[0])
                continue;
            dp[0][i] = max;
        }
        // 对其余的值进行初始化
        int leftup = 0;  // 左上角某个位置的值
        for(int i = 1; i < n; i++)
            for(int j = 1; j<=target; j++){
                leftup = max;
                if(j - arr[i]>=0 && dp[i-1][j - arr[i]] != max){
                    leftup = dp[i-1][j-arr[i]] + 1;
                }
                dp[i][j] = Math.min(leftup,dp[i-1][j]);
            }
        return dp[n-1][target] != max ? dp[n-1][target] : -1;
    }
    // 压缩空间的做法
    public static int minCons2(int[] arr, int target){
        //
        if(arr == null || arr.length == 0){
            return 0;
        }
        int n = arr.length;
        int[] dp = new int[target+1];
        int max = Integer.MAX_VALUE;
        // 初始化
        for(int i = 1; i <= target; i++)
            dp[i] = max;
        if(arr[0] <= target)
            dp[arr[0]] = 1;
        // 初始化结束，下面开始遍历
        int leftup = 0;
        for(int i = 1; i < n; i++)
            for(int j = target; j > 0; j--){
                leftup = max;
                if(j - arr[i] >= 0 && dp[j - arr[i]] != max){
                    leftup = dp[j - arr[i]] + 1;
                }
                dp[j] = Math.min(leftup, dp[j]);
            }
        return dp[target] != max ? dp[target] : -1;
    }
}
