package com.hfx.动态规划;

/**
 * 给定一个矩阵，从左上角开始每次只能向右或者向下走，最后到达右下角的位置，
 * 路径上所有的数字累加起来就是路径和，返回所有路径中最小的路径和
 */
public class 矩阵的最小路径和 {
    public static void main(String[] args) {
        int[][] m = new int[][]{{1,3,5,9},{8,1,3,4},{5,0,6,1},{8,8,4,0}};
        int res = minPathSum2(m);
        System.out.println(res);
    }

    /**
     * 思路：生成一个大小和m一样的数组dp,dp[i][j]表示从dp[0][0]到达该位置的最小路径和，可以先对dp数组的第一行和第一列进行初始化
     * 对数组中余下的值 dp[i][j] = min(dp[i-1][j],dp[i][j-1]) + m[i][j]
     */
    public static int minPathSum1(int[][] m){
        //先进行最初的判断
        if(m == null || m.length == 0 || m[0] == null || m[0].length == 0)
            return 0;
        // 新建一个数组
        int row = m.length;
        int cow = m[0].length;
        int[][] dp = new int[row][cow];
        dp[0][0] = m[0][0];
        // 对第一行进行初始化
        for(int i = 1; i < cow; i++)
            dp[0][i] = m[0][i] + dp[0][i-1];
        // 对第一列进行初始化
        for(int i = 1; i < row; i++)
            dp[i][0] = m[i][0] + dp[i-1][0];
        //对其余的数值进行赋值
        for(int i = 1; i < row; i++)
            for(int j = 1; j < cow; j++){
                dp[i][j] = Math.min(dp[i-1][j],dp[i][j-1]) + m[i][j];
            }
        return dp[row-1][cow-1];
    }
    // 上一种方法的时间复杂度为O(MN),但是所需要的空间复杂度，也是O(MN),本方法通过压缩空间的方式把空间再一次进行压缩
    public static int minPathSum2(int[][] m){
        if(m == null || m.length == 0 || m[0] == null || m[0].length == 0)
            return 0;
        int row = m.length;
        int col = m[0].length;
        int[] arr = new int[col];
        // 初始化
        arr[0] = m[0][0];
        for(int i = 1; i < col; i++)
            arr[i] = arr[i-1] + m[0][i]; //此时arr中的值表示为从m[0][0]到第一行中各个点路径的最小值
        // 向其他行进行滚动
        for(int i = 1; i < row; i++){
            for(int j = 0; j < col; j++){
                if(j == 0)
                    arr[0] = arr[0] + m[i][0];
                else{
                    arr[j] = Math.min(arr[j-1],arr[j]) + m[i][j];
                }
            }
        }
        return arr[col-1];
    }
}
