package 数组;

/**
 * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，
 * 也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
 * 例如:[2,3,1,0,2,5,3]
 * 输出2 或 3
 */
public class P03 {
    public static void main(String[] args){

        int[] nums = new int[]{2,3,1,0,2,5,3};
        int result = findRepeatNumber(nums);
        System.out.println(result);
    }
    // 方法一：先排序，然后在查看相邻元素是否有相同的，如果有，则返回。耗费时间，不推荐
    // 方法二：哈希法，时间复杂度位O(n),但是空间复杂度也为O(n)
    // 方法三：原地置换法，如果没有重复的数字，那么排序置换元素i应该在下标为i的位置
    public static int findRepeatNumber(int[] nums){
        int temp;
        for(int i = 0; i<nums.length; i++){
            while(nums[i] != i){
                if(nums[i] == nums[nums[i]]){
                    return nums[i];
                }else{
                    // 交换的时候需要注意，不能交换错了
                    temp = nums[i];
                    nums[i] = nums[temp];
                    nums[temp] = temp;
                }
            }
        }
        return -1;
    }
}
