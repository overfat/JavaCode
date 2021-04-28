package 数组;

/**
 * 一个整型数组 nums 里除两个数字之外，其他数字都出现了两次。请写程序找出这两个只出现一次的数字。
 * 要求时间复杂度是O(n)，空间复杂度是O(1)。
 */
public class P56 {
    public static void main(String[] args){
        int[] nums = new int[]{1,2,10,4,1,4,3,3};
        int[] result = singleNumbers(nums);
        for(int i : result){
            System.out.println(i);
        }
    }

    public static int[] singleNumbers(int[] nums){
        // 假设结果数为A和B
        int x = 0;
        for(int val : nums)
            x ^= val;

        int flag = x & (-x);

        int res = 0;
        for(int val : nums){
            if((flag & val) != 0){
                res ^= val;
            }
        }
        return new int[]{res, x^res};
    }
}
