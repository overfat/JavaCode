public class 选择书籍 {
    public static void main(String[] args) {
        int[][] scores = new int[][]{{5,4,3,10,1},{8,6,7,3,10},{9,5,3,10,10},{2,6,6,8,5}};
        int[] res = chooseBooks(scores);
        for(int i : res)
            System.out.println(i);
    }
    public static int[] chooseBooks(int[][] scores){
        // 首先找到每个分数组的最大值
        int row = scores.length; //行数
        int col = scores[0].length; //列数
        int[] maxArray = new int[row]; // 保存每一行的最大值
        int[] maxArray2 = new int[col]; //保存每一列的最大值
        int[] res = new int[3];
        for(int i = 0; i<row; i++){
            int max = -1;
            for(int j = 0; j < col; j++){
                if(max < scores[i][j])
                    max = scores[i][j];
            }
            maxArray[i] = max; //把每一行的最大值添加进去
        }
        //  找到了每一行的最大值之后，直接把最大的两个值找到，就是要选择的两本书
        int m1 = -1, m2 = -1;
        int index1 = -1, index2 = -1;
        for(int i = 0; i<row; i++){
            if(m1 < maxArray[i]){
                m1 = maxArray[i];
                index1 = i;
            }
        }
        // 找到了第二个
        for(int i = 0; i<row; i++){
            if(i == index1)
            {
                continue;
            }else {
                if (m2 < maxArray[i]) {
                    m2 = maxArray[i];
                    index2 = i;
                }
            }
        }
        if(index1 < index2){
            res[1] = index1+1;
            res[2] = index2+1;
        }else
        {
            res[1] = index2+1;
            res[2] = index1+1;
        }


        for(int i = 0; i < col; i++){
            //现在需要找到每一列的最大值
            int max2 = -1;
            for(int j = 0; j < row; j++){
                if(max2 < scores[j][i])
                    max2 = scores[j][i];
            }
            maxArray2[i] = max2;

        }
        // 现在需要找到最小值
        int min = Integer.MAX_VALUE;
        int index3 = -1;
        for(int i = 0; i < col; i++){
            if(min > maxArray2[i]){
                min = maxArray2[i];
                index3 = i;
            }
        }
        res[0] = min;
        return res;
    }
}
