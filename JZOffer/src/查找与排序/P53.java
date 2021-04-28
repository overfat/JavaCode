package 查找与排序;

/**
 * 统计一个数字在排序数组中出现的次数。
 */
public class P53 {
    public static void main(String[] args) {
        int[] nums = new int[]{5,7,7,8,8,10};
        int res = search(nums,6);
        System.out.println(res);

    }
    public static int search(int[] nums, int target) {
        if(nums == null || nums.length == 0)
            return 0;
        // 先使用二分查找找到这个值，然后左右探索，找到出现的次数
        int left_times = 0;
        int right_times = 0;
        int low = 0;
        int high = nums.length - 1;
        int index = biSearch(nums,low,high,target);
        if(index == -1)
            return 0;
        for(int i = index; i >= 0; i--)
            if(nums[i] == target)
                left_times++;
        for(int i = index; i < nums.length; i++)
            if(nums[i] == target)
                right_times++;
        return left_times+right_times-1;
    }
    public static int biSearch(int[] nums,int low, int high, int target){
        int index = -1;
        if(low > high)
            return -1;
        int middle = (int)(low+high)/2;
        if(nums[middle] == target)
            return middle;

        if(nums[middle] > target){
            high = middle - 1;
            index = biSearch(nums,low,high,target);
        }else{
            low = middle + 1;
            index = biSearch(nums,low,high,target);
        }
        return index;
    }
}
