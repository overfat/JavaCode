package 位运算;

/**
 * 一个整型数组 nums 里除两个数字之外，其他数字都出现了两次。请写程序找出这两个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)。
 */
public class P56 {
    public static void main(String[] args) {
        int[] nums = new int[]{1,2,10,4,1,4,3,3};
        int[] res = singleNumbers(nums);
        for(int i : res)
            System.out.println(i);
    }
    public static int[] singleNumbers(int[] nums){
        // 使用分组异或的思想
        int num = 0,x = 0,y = 0,m = 1;
        //对所有的数据进行异或
        for(int i : nums)
            num ^= i;
        //得到的结果就是不同的两个值异或得到的结果
        while((num & m) == 0)
            m <<= 1;
        for(int i : nums){
            if((i & m) == 0) x ^= i;
            else
                y ^= i;
        }
        return new int[]{x,y};
    }
}
