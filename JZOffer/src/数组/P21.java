package 数组;

/**
 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数位于数组的前半部分，所有偶数位于数组的后半部分。
 * 输入 [1,2,3,4]
 * 输出 [1,3,2,4]
 */
public class P21 {
    public static void main(String[] args){
        int[] nums = new int[]{1,2,3,4,5,6};
        int[] result = exchange(nums);
        for(int i: result){
            System.out.println(i);
        }
    }

    public static int[] exchange(int[] nums){
        // 首先判断是不是为空
        if(nums == null)
            return null;
        // 交换数组中元素顺序
        int temp;
        int left = 0;
        int right = nums.length-1;
        while(left < right){
            while(left < right && nums[left] % 2 == 1){
                left++ ;
            }
            while(left < right && nums[right] % 2 == 0){
                right--;
            }
            // 交换两个位置
            if(left < right){
                temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
            }
        }
        return nums;
    }
}
