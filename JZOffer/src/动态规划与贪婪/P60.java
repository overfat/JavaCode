package 动态规划与贪婪;

/**
 *
 */
public class P60 {
    public static void main(String[] args){
        double[] res = dicesProbability(2);
        for(double i : res){
            System.out.println(i);
        }
    }
    public static double[] dicesProbability(int n){
        // 利用动态规划的方式来解决问题
        double[] dp = new double[6];
        for(int i = 0; i < dp.length; i++){
            dp[i] = 1.0/6.0;
        } // 完成对数组的初始化
        for(int i = 2; i <= n; i++){
            // 新建一个数组，里面包含了可能出现的数值
            double[] tmp = new double[5*i+1];
            // 对数组中的内容进行初始化，需要利用到dp数组
            for(int j = 0; j < dp.length; j++){
                for(int k = 0; k < 6; k++){
                    tmp[j+k] += dp[j]/6.0;
                }
            }
            dp = tmp;
        }
        return dp;
    }
}
