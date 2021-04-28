package 回溯法;

/**
 * 请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。路径可以从矩阵中的任意一格开始，
 * 每一步可以在矩阵中向左、右、上、下移动一格。如果一条路径经过了矩阵的某一格，那么该路径不能再次进入该格子。
 * 例如，在下面的3×4的矩阵中包含一条字符串“bfce”的路径（路径中的字母用加粗标出）。
 */
public class P12 {
    public static void main(String[] args) {
        char[][] board = new char[][]{{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
        String str = "ABCCED";
        boolean result = exist(board, str);
        System.out.println(result);
    }
    public static boolean exist(char[][] board, String word) {
        if(board == null){
            return false;
        }
        if(word.length() == 0)
            return true;
        if(board.length == 0 || (board.length != 0 && board[0].length == 0))
            return false;
        char[] wordArray = word.toCharArray();
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(dfs(board,wordArray, i, j, 0))
                    return true;
            }
        }
        return false;
    }

    private static boolean dfs(char[][] board, char[] wordArray, int i, int j, int k) {
        // 先判断边界问题
        if(i >= board.length || i < 0 || j >= board[0].length || j < 0 || board[i][j] != wordArray[k]) return false;
        // 当k的值到了数组的最后一个之后，就为true了
        if(k == wordArray.length -1) return true;
        // 找到之后，需要把当前位置设置为空，以免再次被访问
        board[i][j] = ' ';
        boolean res = dfs(board,wordArray,i+1,j,k+1) || dfs(board,wordArray,i-1,j,k+1) ||
                dfs(board,wordArray,i,j+1,k+1) || dfs(board,wordArray,i,j-1,k+1);
        // 当前搜索过后，需要把当前元素设置为原来的值，以免影响下次访问
        board[i][j] = wordArray[k];
        return res;
    }
}
