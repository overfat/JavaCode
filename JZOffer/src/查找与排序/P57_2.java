package 查找与排序;

import java.util.ArrayList;
import java.util.List;

/**
 * 输入一个正整数 target ，输出所有和为 target 的连续正整数序列（至少含有两个数）。
 * 序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。
 */

public class P57_2 {
    public static void main(String[] args) {
        int[][] res = findContinousSequence(9);
        for(int[] i : res)
            for(int j : i){
                System.out.println(j);
            }
    }
    public static int[][] findContinousSequence(int target){
        // 滑动窗口的思想
        int i = 1, j = 2, s = 3;
        List<int[]> res = new ArrayList<>();
        while(i < j){
            if(s == target){
                int[] ans = new int[j - i + 1];
                for(int k = 0; k<ans.length; k++)
                    ans[k] = i+k;
                res.add(ans);
            }
            if(s >= target){
                s = s - i;
                i++;
            }else{
                j++;
                s += j;
            }
        }
        return res.toArray(new int[0][]);
    }
}
