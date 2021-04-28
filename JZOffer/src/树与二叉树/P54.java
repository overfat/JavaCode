package 树与二叉树;

/**
 * 给定一棵二叉搜索树，请找出其中第k大的节点。
 */

public class P54 {
    static int res, k;
    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(5);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(6);
        TreeNode node4 = new TreeNode(2);
        TreeNode node5 = new TreeNode(4);
        TreeNode node6 = new TreeNode(1);
        TreeNode node8 = new TreeNode(8);
        TreeNode node9 = new TreeNode(7);
        node1.left = node2;
        node1.right = node3;
        node3.right = node8;
        node3.left = node9;
        node2.left = node4;
        node2.right = node5;
        node4.left = node6;
        int res = kthLargest(node1,5);
        System.out.println(res);
    }

    /**
     * 二叉搜索树特点，中序遍历序列为递增数组。 如果先遍历右子树，在遍历中节点，然后在遍历左子树，则是递减序列。
     */
    public static int kthLargest(TreeNode root, int k) {
        int[] res = new int[k];
        recur(root,0,res,k);
        return -1;
    }
    public static void recur(TreeNode root, int count,int[] array, int k){
        if(root.right != null)
            recur(root.right,count,array,k);
        array[count++] = root.val;
        if(count == k)
            return;
        if(root.left != null)
            recur(root.left, count,array,k);

    }
}
