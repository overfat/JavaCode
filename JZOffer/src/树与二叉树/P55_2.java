package 树与二叉树;

/**
 * 输入一棵二叉树的根节点，判断该树是不是平衡二叉树。如果某二叉树中任意节点的左右子树的深度相差不超过1，那么它就是一棵平衡二叉树。
 */

public class P55_2 {
    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        node1.right = node2;
        boolean flag = isBalanced(node1);
        System.out.println(flag);

    }


    public static boolean isBalanced(TreeNode root) {
        if(root == null){
            return true;
        }
        return Math.abs(maxDepth(root.left) - maxDepth(root.right)) <=1 && isBalanced(root.left) && isBalanced(root.right);
    }

    public static int maxDepth(TreeNode root){
        //求二叉树的深度
        if(root == null)
            return 0;
        return Math.max(maxDepth(root.left),maxDepth(root.right)) + 1;
    }
}
