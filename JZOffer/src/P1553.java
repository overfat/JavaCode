import java.util.HashMap;

/**
 * 厨房里总共有 n个橘子，你决定每一天选择如下方式之一吃这些橘子：
 *
 * 吃掉一个橘子。
 * 如果剩余橘子数 n能被 2 整除，那么你可以吃掉 n/2 个橘子。
 * 如果剩余橘子数n能被 3 整除，那么你可以吃掉 2*(n/3) 个橘子。
 * 每天你只能从以上 3 种方案中选择一种方案。
 *
 * 请你返回吃掉所有 n个橘子的最少天数。
 *
 */
public class P1553 {
    public static void main(String[] args) {
        for(int i = 5; i<=100; i++){
            int res = minDays(i);
            int res2 = minDays2(i);
            System.out.println(i + " : " + res + " : " + res2);
        }
    }
    public static int minDays(int n){
        int[] res = new int[n+1];
        res[1] = 1;
        res[2] = 2;
        res[3] = 2;
        res[4] = 3;

        for(int i = 5; i<=n;i++){
            // 判断这个值
            if((i%2 == 0 && i % 3 == 0) || i % 3 == 0){
                res[i] = 1 + res[i/3];
                continue;
            }
            if(i % 2 == 0 && i % 3 == 1){
                res[i] = Math.min(1+res[i-1], 1+res[i/2]);
                continue;
            }
            if(i % 2 == 0){
                res[i] = 1 + res[i/2];
                continue;
            }
            if(i % 3 == 1){
                res[i] = 1 + res[i-1];
                continue;
            }
            if(i % 3 == 2 && i%2 == 1){
                res[i] = Math.min(2+res[i-2],1+res[i-1]);
                continue;
            }

        }
        return res[n];
    }
    static HashMap<Integer,Integer> memo = new HashMap<>();
    public static int minDays2(int n){
        if(n <= 1){
            return n;
        }
        if(memo.containsKey(n)){
            return memo.get(n);
        }
        memo.put(n, Math.min(n%2 + 1 + minDays2(n/2), n%3 + 1 + minDays2(n/3)));
        return memo.get(n);
    }
}
