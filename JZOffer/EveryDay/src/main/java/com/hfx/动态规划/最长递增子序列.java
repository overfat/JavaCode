package com.hfx.动态规划;

/**
 * 给定数组arr，返回arr的最长递增子序列
 * 例如：arr = [2,1,5,3,6,4,8,9,7],最长递增子序列为1，3，4，8，9
 */
public class 最长递增子序列 {
    public static void main(String[] args) {
        int[] arr = new int[]{2,1,5,3,6,4,8,9,7};
        int[] res = getdp1(arr);
        int[] lis = generateLIS(arr, res);
        for(int i : lis)
            System.out.println(i);
    }
    // 求最大递增子序列的长度
    public static int[] getdp1(int[] arr){
        int[] dp = new int[arr.length]; //构建dp数组，dp[i]表示以arr[i]结尾的数值的递增子序列长度
        int n = arr.length;
        for(int i = 0; i < n; i++)
        {
            dp[i] = 1;
            for(int j = 0; j < i; j++){
                if(arr[j] < arr[i]){
                    dp[i] = Math.max(dp[i],dp[j] + 1);
                }
            }
        }
        return dp;
    }
    // 根据最大递增子序列的长度去反推具体的值
    public static int[] generateLIS(int[] arr, int[] dp){
        int len = 0;
        int index = 0;
        // 先找到最大的递增子序列以及其索引值
        for(int i = 0; i < dp.length; i++){
            if(dp[i] > len){
                len = dp[i];
                index = i;
            }
        }
        int[] lis = new int[len];
        lis[--len] = arr[index];
        // 从index位置开始反推，找到arr[j] < arr[index]并且dp[j] = dp[index] - 1
        for(int j = index; j >= 0; j--){
            if(arr[j] < arr[index] && dp[j] == dp[index] - 1){
                lis[--len] = arr[j];
                index = j;
            }
        }
        return lis;
    }
}
