package 每日一题;

import org.junit.Test;

/**
 * 给你两个有序整数数组nums1 和 nums2，请你将 nums2 合并到nums1中，使 nums1 成为一个有序数组。
 *
 * 初始化nums1 和 nums2 的元素数量分别为m 和 n 。你可以假设nums1 的空间大小等于m + n，这样它就有足够的空间保存来自 nums2 的元素。
 */
public class P88 {
    @Test
    public void test() {
        int[] nums1 = new int[]{0};
        int[] nums2 = new int[]{1};
        int m = 0;
        int n = 1;
        merge(nums1,m,nums2,n);
        for(int i = 0; i < nums1.length; i++){
            System.out.println(nums1[i]);
        }
    }
    // 利用逆向双指针的思路，每次找到最大的那个值，然后赋值，赋值之后依次把值减减
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int tail = m + n - 1;
        int p = m - 1;
        int q = n - 1;

        while(p >= 0 || q >= 0){
            if(p == -1){
                nums1[tail--] = nums2[q--];
            }else if(q == -1){
                nums1[tail--] = nums1[p--];
            }else if(nums1[p] > nums2[q]){
                nums1[tail--] = nums1[p--];
            }else{
                nums1[tail--] = nums2[q--];
            }
        }
    }
}
