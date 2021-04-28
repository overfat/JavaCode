package 动态规划与贪婪;

/**
 * 假设把某股票的价格按照时间先后顺序存储在数组中，请问买卖该股票一次可能获得的最大利润是多少？
 * [7,1,5,3,6,4] 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 * 注意卖出的价格要大于买入价格
 */

public class P63 {
    public static void main(String[] args) {
        int[] prices = new int[]{7,1,5,3,6,4};
        int res = maxProfit(prices);
        System.out.println(res);
    }
    public static int maxProfit(int[] prices){
        // 找到最大的差值，动态规划题目
        int cost = Integer.MAX_VALUE;
        int profit = 0;
        for(int price : prices){
            cost = Math.min(cost, price);
            profit = Math.max(profit,price - cost);
        }

        return profit;
    }
}
