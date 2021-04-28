package 动态规划与贪婪;

/**
 * 输入一个整型数组，数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。
 *
 * 要求时间复杂度为O(n)。
 */

public class P42 {
    public static void main(String[] args) {
        int[] nums = new int[]{-2,1,-3,4,-1,2,1,-5,4};
        int res = maxSubArray(nums);
        System.out.println(res);
    }

    public static int maxSubArray(int[] nums) {
        int max = nums[0];
        for(int i = 1; i < nums.length; i++){
            if(nums[i-1] >= 0)
                nums[i] = nums[i] + nums[i-1];
            if(max < nums[i])
                max = nums[i];
        }
        return max;
    }
}
