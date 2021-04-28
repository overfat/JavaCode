package 数组;

public class P29 {
    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1,2,3,4},{5,6,7,8},{9,10,11,12}};
        int[] res = spiralOrder(matrix);
        for(int i : res)
            System.out.println(i);
    }
    public static int[] spiralOrder(int[][] matrix) {
        if(matrix == null || matrix.length == 0)
            return new int[0];
        if(matrix.length == 1 && matrix[0].length == 0)
            return new int[0];
        // 定义上下左右四个值
        int l = 0;
        int r = matrix[0].length-1;
        int t = 0;
        int b = matrix.length-1;
        int[] res = new int[matrix.length * matrix[0].length];
        int count = 0;
        // 定义循环
        while(true){
            // 从左到右
            for(int i = l; i <= r; i++)
                res[count++] = matrix[t][i];
            t = t + 1;
            if(t > b) break;
            // 从上到下
            for(int i = t; i <= b; i++)
                res[count++] = matrix[i][r];
            r = r - 1;
            if(r < l) break;
            // 从右到左
            for(int i = r; i >= l; i--)
                res[count++] = matrix[b][i];
            b = b - 1;
            if(b < t) break;
            // 从下到上
            for(int i = b; i >= t; i--)
                res[count++] = matrix[i][l];
            l = l + 1;
            if(l > r) break;
        }
        return res;
    }

}
