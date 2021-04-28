package 每日一题;

/**
 * 已知存在一个按非降序排列的整数数组 nums ，数组中的值不必互不相同。
 * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转 ，
 * 使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。
 * 例如， [0,1,2,4,4,4,5,6,6,7] 在下标 5 处经旋转后可能变为 [4,5,6,6,7,0,1,2,4,4] 。
 */
public class P81 {
    public static void main(String[] args) {
        int[] nums = new int[]{4,5,6,6,7,4,4,4,4,4};
        boolean flag = search(nums, 7);
    }
    public static boolean search(int[] nums, int target){
        if(nums == null || nums.length == 0)
            return false;
        int minIndex = minArray(nums);
        // 判断最小值的大小与target之间的关系
        if(nums[minIndex] > target)
            return false;
        if(minIndex == 0 && target > nums[nums.length - 1])
            return false;

        return false;
    }
    public static int minArray(int[] numbers){
        /**
         * 方法一：可直接对数组进行排序，然后输出最小值即可。
         * 方法二：利用二分法，找到最小值,本题目采用方法
         */
        int l = 0, r = numbers.length - 1;
        while (l < r) {
            int mid = ((r - l) >> 1) + l;
            //只要右边比中间大，那右边一定是有序数组
            if (numbers[r] > numbers[mid]) {
                r = mid;
            } else if (numbers[r] < numbers[mid]) {
                l = mid + 1;
                //去重
            } else r--;
        }
        // 这里返回的是最小值的位置
        return l;
    }
}
