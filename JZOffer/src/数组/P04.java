package 数组;

/**
 * 二维数组中的查找
 * 在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
 * 请完成一个高效的函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 *
 */
public class P04 {
    public static void main(String[] args){
//        int[][] matrix = new int[][]{{1,4,7,11,15},{2,5,8,12,19},{3,6,9,16,22},{10,13,14,17,24},{18,21,23,26,30}};
        int[][] matrix = new int[][]{{1,3,5}};
        int target = 4;
        boolean result = findNumberIn2DArray(matrix, target);
        System.out.println(result);
    }

    public static boolean findNumberIn2DArray(int[][] matrix, int target) {
        // 首先需要判断这个数组是否有效
        if (matrix == null || matrix.length <= 0)
            return false;
        //直接从右上角开始比，如果大于target,列减一，如果小于target，行加一
        int m = matrix.length;
        int n = matrix[0].length;
        int row = 0;
        int col = n - 1;
        while(row < m && col >=0){
            if(matrix[row][col] < target){
                row ++;
            }else if(matrix[row][col] > target){
                col--;
            }else{
                return true;
            }
        }
        return false;
    }
}
