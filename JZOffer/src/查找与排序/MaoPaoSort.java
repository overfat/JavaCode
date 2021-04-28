package 查找与排序;

// 冒泡排序
public class MaoPaoSort {
    public static void main(String[] args) {
        int[] arr = new int[]{2,0,1,4,2,3,3,8,10,5};
        int[] result = sort(arr);
        for(int i : result)
            System.out.println(i);
    }
    //冒泡排序
    public static int[] sort(int[] arr){
        if(arr == null)
            return null;
        if(arr.length == 0){
            return new int[0];
        }
        // 冒泡排序要进行双重循环，把下面的值冒泡上来
        int temp = 0;
        for(int i = 0; i < arr.length; i++){
            for(int j = i+1; j < arr.length; j++){
                if(arr[i] > arr[j]){
                    temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        return arr;
    }
}
