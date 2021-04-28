package 查找与排序;

/**
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。
 * 例如，数组[3,4,5,1,2] 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为1。
 *
 */
public class P11 {
    public static void main(String[] args){
        int[] numbers = new int[]{2,2,2,0,1};
        int result = minArray(numbers);
        System.out.println(result);
    }
    public static int minArray(int[] numbers){
        /**
         * 方法一：可直接对数组进行排序，然后输出最小值即可。
         * 方法二：利用二分法，找到最小值,本题目采用方法
         */
        int l = 0, r = numbers.length - 1;
        while (l < r) {
            int mid = l +  ((r - l) >> 1);
            //只要右边比中间大，那右边一定是有序数组
            if (numbers[r] > numbers[mid]) {
                r = mid;
            } else if (numbers[r] < numbers[mid]) {
                l = mid + 1;
                //去重
            } else r--;
        }
        return numbers[l];
    }
}
