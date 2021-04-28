package 每日一题;

import java.util.TreeSet;

/**
 * 给你一个整数数组 nums 和两个整数k 和 t 。请你判断是否存在 两个不同下标 i 和 j，
 * 使得abs(nums[i] - nums[j]) <= t ，同时又满足 abs(i - j) <= k 。
 * 如果存在则返回 true，不存在返回 false。
 *
 * 例如：
 * 输入：nums = [1,2,3,1], k = 3, t = 0
 * 输出：true
 */
public class P220_3 {
    public static void main(String[] args) {
//        int[] nums = new int[]{1,5,9,1,5,9};
//        int[] nums = new int[]{2147483647,-1,2147483647};
        int[] nums = new int[]{7,1,3};
        int k = 2;
        int t = 3;
        boolean flag = containsNearbyAlmostDuplicate(nums,k,t);
        System.out.println(flag);
    }
    public static boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        //暴力算法
        if(nums == null)
            return false;
        boolean flag = false;
        for(int i = 0; i < nums.length - 1; i++){
            for(int j = i+1; j < nums.length; j++){
                if(Math.abs(j - i) <= k)
                {
                    long nums_1 = nums[i];
                    long nums_2 = nums[j];
                    long value = nums_1 - nums_2;
                    long abs_value = Math.abs(value);
                    if(abs_value <= t){
                        flag = true;
                        break;
                    }
                }
                else
                    break;
            }
            if(flag)
                break;
        }
        return flag;
    }
    public static boolean containsNearbyAlmostDuplicate2(int[] nums, int k, int t) {
        int n = nums.length;
        TreeSet<Long> set = new TreeSet<Long>();
        for (int i = 0; i < n; i++) {
            Long ceiling = set.ceiling((long) nums[i] - (long) t);
            if (ceiling != null && ceiling <= (long) nums[i] + (long) t) {
                return true;
            }
            set.add((long) nums[i]);
            if (i >= k) {
                set.remove((long) nums[i - k]);
            }
        }
        return false;

    }
}
