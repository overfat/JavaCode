package 数学;

/**
 * 从扑克牌中随机抽5张牌，判断是不是一个顺子，即这5张牌是不是连续的。
 * 2～10为数字本身，A为1，J为11，Q为12，K为13，而大、小王为 0 ，可以看成任意数字。A 不能视为 14。
 */
public class P61 {
    public static void main(String[] args) {
        int[] nums = new int[]{1,2,2,4,5};
        boolean flag = isStraight(nums);
        System.out.println(flag);
    }
    public static boolean isStraight(int[] nums) {
        // 除了0之外，最大值减去最小值小于5就可以了
        // 除了0可以重复之外，其他值不能重复
        if(nums == null || nums.length <5)
            return false;
        int min = 15;
        int max = -1;
        int[] array = new int[5];
        for(int i = 0; i<nums.length; i++){
            if(nums[i] == 0)
                continue;
            // 比较
            if(nums[i] < min)
                min = nums[i];
            if(nums[i] > max)
                max = nums[i];
            array[i] = nums[i];
            boolean flag = valueInArray(nums[i], array,i-1);
            if(!flag)
                return false;
        }
        return max - min < 5;
    }

    private static boolean valueInArray(int num, int[] nums,int length) {
        if(length < 0){
            return true;
        }
        for(int i = 0; i<=length;i++)
            if(num == nums[i])
                return false;

        return true;
    }
}
