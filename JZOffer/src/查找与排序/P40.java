package 查找与排序;

/**
 * 输入整数数组，找出其最小的n个数,本题目考察的是排序，借此机会整理常见的排序方法
 */
public class P40 {
    public static void main(String[] args) {
        int[] arr = new int[]{2,0,1,4,2,3,3,8,10,5};
        int k = 4;
        int[] result = getLeastNumbers(arr,k);
        for(int i : result)
            System.out.println(i);
    }
    /**
     * 快速排序: 要找到切分点
     */
    public static int[] getLeastNumbers(int[] arr, int k) {
        if(arr == null)
            return null;
        if(k > arr.length){
            return null;
        }
        if( arr.length == 0 && k == 0){
            return new int[0];
        }
        int high = arr.length;
        quickSort(arr,0,high-1);
        int[] result = new int[k];
        for(int i = 0; i<k;i++)
            result[i] = arr[i];
        return result;
    }

    public static void quickSort(int[] arr, int low, int high){
        if(high <= low)
            return;
        // 找到切分点
        int j = partition(arr,low, high);
        quickSort(arr,low, j-1);
        quickSort(arr,j+1,high);
    }

    private static int partition(int[] arr, int low, int high) {
        int i = low;
        int j = high + 1;
        int temp;
        while (true){
            while(arr[++i] < arr[low]) if(i == high) break;
            while(arr[--j] > arr[low]) if(j == low)  break;
            if(i >= j) break;
            // 交换i和j的值
            temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
        // 在把arr[low]放到正确的位置上，结束
        temp = arr[low];
        arr[low] = arr[j];
        arr[j] = temp;
        return j;
    }

}
