package 查找与排序;

/**
 * 堆排序算法
 */
public class HeapSort {
    public static void main(String[] args) {
        int[] arr = new int[]{2,0,1,4,2,3,3,8,10,5};
        sort(arr);
    }

    public static void sort(int[] arr){
        if(arr == null || arr.length == 1){
            return;
        }
        // 找到第一个非叶子节点
        int index = (int)Math.floor(arr.length/2) - 1;
        // 从第一个非叶子节点开始调整
        for(int i = index; i >= 0; i--)
            buildMaxHeap(arr,arr.length-1,i);
        // 循环结束，大顶堆已经构建完毕，接下来依次取值
        for(int i = 0; i < arr.length; i++){
            // 交换第一个元素和最后一个元素
            int max = arr[0];
            int temp = arr[0];
            arr[0] = arr[arr.length - i - 1];
            arr[arr.length - i - 1] = temp;
            buildMaxHeap(arr, arr.length - i - 2, 0);
            System.out.println(max);
        }
    }

    //创建小顶堆
    public static void buildMinHeap(int[] arr, int length, int index){
        // 首先找到左右孩子的坐标
        int left = 2*index + 1;
        int right = 2*index + 2;
        int minIndex = index;
        // 判断左右孩子是否超出范围
        if(left <= length && arr[left] < arr[minIndex]){
            minIndex = left;
        }
        if(right <= length && arr[right] < arr[minIndex]){
            minIndex = right;
        }
        // 判断是否交换了节点
        if(minIndex != index){
            //交换
            int temp = arr[index];
            arr[index] = arr[minIndex];
            arr[minIndex] = temp;
            // 交换之后还要继续判断
            buildMaxHeap(arr,length, minIndex);
        }
    }


    // 创建大顶堆
    public static void buildMaxHeap(int[] arr, int length, int index) {
        // 首先找到其左右孩子的坐标
        int left = 2*index + 1;
        int right = 2*index + 2;
        // 判断左右孩子节点的坐标是否超出length范围
        int maxIndex = index;
        if(left <= length && arr[left] > arr[maxIndex]){
            maxIndex = left;
        }
        // 接着比较右子树
        if(right <= length && arr[right] > arr[maxIndex]){
            maxIndex = right;
        }
        // 接下来比较maxindex与index的值
        if(maxIndex != index){
            // 交换
            int temp = arr[index];
            arr[index] = arr[maxIndex];
            arr[maxIndex] = temp;

            // 接下来继续看交换之后的堆 是否满足情况
            buildMaxHeap(arr,length,maxIndex);
        }
    }
}
