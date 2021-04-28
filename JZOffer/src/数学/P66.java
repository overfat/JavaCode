package 数学;

/**
 * 给定一个数组 A[0,1,…,n-1]，请构建一个数组 B[0,1,…,n-1]，其中B[i] 的值是数组 A 中除了下标 i 以外的元素的积,
 * 即B[i]=A[0]×A[1]×…×A[i-1]×A[i+1]×…×A[n-1]。不能使用除法。
 *
 */

public class P66 {
    public static void main(String[] args) {
        int[] a = new int[]{1,2,3,4,5};
        int[] res = constructAr(a);
        for(int i : res)
            System.out.println(i);
    }
    public static int[] constructAr(int[] a){
        //可以构建两个数组，第一个数组表示该位置前面数组的乘积，第二个数组表示该位置后面元素的乘积，再把两个数组相乘即可
        int[] b = new int[a.length];
        int tmp = 1;
        for(int i = 0; i < a.length; i++){
            if(i == 0){
                b[i] = 1;
                continue;
            }
            b[i] = a[i-1]*b[i-1];
        }

        for(int i = a.length - 2; i>=0; i--){
            tmp *= a[i+1];
            b[i] *= tmp;
        }
        return b;
    }
}
