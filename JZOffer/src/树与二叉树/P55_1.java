package 树与二叉树;

/**
 * 输入一棵二叉树的根节点，求该树的深度。从根节点到叶节点依次经过的节点（含根、叶节点）形成树的一条路径，最长路径的长度为树的深度。
 */
public class P55_1 {
    public static void main(String[] args) {
        TreeNode root = Test1.buildTree();
        int dep = maxDepth(root,0);
        System.out.println(dep);
    }
    public static int maxDepth(TreeNode root,int depth) {
        if(root == null){
            return 0;
        }
//        int depth = 1;
        dfs(root,depth);
        return depth;
    }
    public static void dfs(TreeNode root, int depth){
        if(root == null)
            return;
        dfs(root.left,depth+1);
        dfs(root.right,depth+1);
    }
}
