package 每日一题;

/**
 * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。
 *
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 *
 */

public class P26 {
    public static void main(String[] args) {
        int[] nums = new int[]{0,0,1,1,1,2,2,3,3,4};
        int length = removeDuplicates(nums);
        for(int i = 0; i<length;i++)
            System.out.println(nums[i]);
    }
    //双指针的做法
    public static int removeDuplicates(int[] nums){
        if(nums.length <= 1)
            return nums.length;
        // 定义快慢指针
        int slow = 0;
        int fast = 1;
        while(fast < nums.length){
            if(nums[fast] == nums[slow])
            {
                fast++;
                continue;
            }
            nums[++slow] = nums[fast++];
        }

        return slow+1;
    }
}
