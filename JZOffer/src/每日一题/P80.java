package 每日一题;

public class P80 {
    public static void main(String[] args) {
        int[] nums = new int[]{1,1,1,1,2,2,2,2,3,3,4,5,5,5,6,7,8,8,8,8,8};
//        int[] nums = new int[]{1,1,1,2,2,3};
        int res = removeDuplicates(nums);
        for(int i = 0; i<res;i++)
            System.out.println(nums[i]);
    }
    public static int removeDuplicates(int[] nums){
        //
        if(nums == null || nums.length <= 2){
            return nums.length;
        }

        int[] nums2 = new int[nums.length];
        int count = 1;
        int point = 0;
        for(int i = 0; i < nums.length; i++){
            nums2[point++] = nums[i];
            if(i+1 <nums.length && nums[i] == nums[i+1]){
                count++;
            }else{
                count = 1;
            }
            if(count >=3){
                // 需要找到下一个赋值点，第一个与i+1值不同的点
                boolean flag = false;
                for(int j = i+1; j<nums.length; j++){
                    if(nums[j] != nums[i+1])
                    {
                        // 判断这个值有几个
                        flag = true;
                        int count2 = 1;
                        for(int k = j; k<nums.length - 1; k++){
                            if(nums[k] == nums[k+1])
                                count2++;
                            else
                                break;
                        }
                        if(count2 == 1) {
                            nums2[point++] = nums[j]; //赋值结束，要找到下一个循环的开始
                            i = j+1-1;
                            count = 1;
                            break;
                        }
                        if(count2 == 2)
                        {
                            nums2[point++] = nums[j];
                            nums2[point++] = nums[j+1];
                            i = j + 2-1;
                            count = 1;
                            break;
                        }
                        if(count2 >= 3){
                            nums2[point++] = nums[j];
                            nums2[point++] = nums[j+1];
                            i = j + count2-1;
                            count = 1;
                            break;
                        }

                    }
                }
                if(flag == false)
                    break;

            }
        }
        return point;
    }

    public static int removeDuplicates2(int[] nums){
        int n = nums.length;
        if(n <= 2)
            return n;
        int slow = 2;
        int fast = 2;
        while(fast < n){
            if(nums[slow - 2] != nums[fast]){
                nums[slow] = nums[fast];
                ++slow;
            }else{
                fast++;
            }
        }
        return slow;
    }
}
