package 查找与排序;

/**
 * 一个长度为n-1的递增排序数组中的所有数字都是唯一的，并且每个数字都在范围0～n-1之内。
 * 在范围0～n-1内的n个数字中有且只有一个数字不在该数组中，请找出这个数字。
 */


public class P53_2 {
    public static void main(String[] args) {
        int[] nums = new int[]{0};
        int res = missingNumber(nums);
        System.out.println(res);
    }
    public static int missingNumber(int[] nums) {
        if(nums[0] != 0)
            return 0;

        int high = nums.length - 1;
        int low = 0;
        int mid;

        while(low <= high){
            mid = (low+high)/2;
            if(mid == nums[mid])
                low = mid + 1;
            else{
                if(nums[mid] - nums[mid-1] == 2)
                    return mid;
                else{
                    high = mid - 1;
                }
            }
        }
        return low;
    }
}
