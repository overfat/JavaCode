package 树与二叉树;
// 完成一个函数，输入一个二叉树，该函数输出它的镜像
public class P27 {
    public static void main(String[] args){

    }

    public static TreeNode mirroeTree(TreeNode root){
        // 代码在leetcode已经测试通过了
        if(root == null){
            return null;
        }
        TreeNode temp = root.left;
        root.left = mirroeTree(root.right);
        root.right = mirroeTree(temp);
        return root;
    }
}
