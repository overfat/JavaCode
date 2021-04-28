package 栈与队列;

/**
 * 给定一个数组 nums 和滑动窗口的大小 k，请找出所有滑动窗口里的最大值。
 */
public class P59 {
    public static void main(String[] args){
        int[] nums = new int[]{1,3,-1,-3,5,3,6,7};
        int[] result = maxSlidingWindow(nums, 3);
        for(int i : result){
            System.out.println(i);
        }
    }
    public static int[] maxSlidingWindow(int[] nums, int k){
        // 首先判断有多少最大值
        if(nums == null || nums.length == 0){
            return nums;
        }
        int length = nums.length - k + 1;
        int[] result = new int[length]; // 数组的长度
        int start = 0;
        int end = k - 1;
        int temp = Integer.MIN_VALUE;
        int index = -1; //记录索引的位置
        for(int i = 0; i < length; i++){
            // 第一个滑动窗口
            if(index < start) {
                temp = Integer.MIN_VALUE;
                for (int j = start; j <= end; j++) {
                    // 找到最大值的索引
                    if (temp < nums[j]) {
                        temp = nums[j];
                        index = j;
                    }
                }
            }else{
                // 接下来要判断temp和nums[end]的大小
                if(temp < nums[end]){
                    temp = nums[end];
                    index = end;
                }
            }
            result[i] = temp;
            start++;
            end++;
        }
        return result;
    }
}
