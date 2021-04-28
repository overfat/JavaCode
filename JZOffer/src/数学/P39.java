package 数学;

/**
 * 数组中 出现超过一半的数字
 */
public class P39 {
    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3,2,2,2,5,4,2};
        int res = majorityElement(nums);
        System.out.println(res);
    }

    public static int majorityElement(int[] nums) {
//        if( nums == null)
//            return null;
        // 思路，如果一个数和另一个数不同，则抵消掉，最后剩下的数一定是所要的结果
        int temp = 0;
        int number = 0;
        for(int i = 0; i < nums.length; i++){
            if(temp == 0){
                number = nums[i];
                temp = 1;
            }else{
                if(nums[i] == number){
                    temp++;
                }else{
                    temp--;
                }
            }

        }
        return number;

    }
}
