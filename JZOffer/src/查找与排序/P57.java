package 查找与排序;

/**
 * 输入一个递增排序的数组和一个数字s，在数组中查找两个数，使得它们的和正好是s。如果有多对数字的和等于s，则输出任意一对即可。
 */

public class P57 {
    public static void main(String[] args) {
        int[] nums = new int[]{2,7,11,15};
        int[] res = twoSum(nums, 9);
        for(int i : res)
            System.out.println(i);
    }
    public static int[] twoSum(int[] nums, int target){
        // 利用双指针法
        if(nums == null || nums.length <= 1){
            return new int[0];
        }
        int[] res = new int[2];
        int count = 0;
        int i = 0;
        int j = nums.length - 1;
        if(nums[i] >= target){
            return new int[0];
        }
        // 接下来才是判断
        while(i < j){
            if(nums[i] + nums[j] == target){
                res[count++] = nums[i];
                res[count++] = nums[j];
                break;
            }
            if(nums[i] + nums[j] > target){
                j--;
            }
            if(nums[i] + nums[j] < target){
                i++;
            }
        }
        if(count == 2){
            return res;
        }else{
            return new int[0];
        }
    }
}
