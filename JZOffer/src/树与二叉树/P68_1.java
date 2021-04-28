package 树与二叉树;

/**
 * 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
 */
public class P68_1 {
    public static void main(String[] args) {
        TreeNode node6 = new TreeNode(6);
        TreeNode node2 = new TreeNode(2);
        TreeNode node8 = new TreeNode(8);
        TreeNode node0 = new TreeNode(0);
        TreeNode node4 = new TreeNode(4);
        TreeNode node7 = new TreeNode(7);
        TreeNode node9 = new TreeNode(9);
        TreeNode node3 = new TreeNode(3);
        TreeNode node5 = new TreeNode(5);
        node6.left = node2;
        node6.right = node8;
        node2.left = node0;
        node2.right = node4;

        node8.left = node7;
        node8.right = node9;
        node4.left = node3;
        node4.right = node5;
        TreeNode result = lowestCommonAncestor(node6,node2,node8);
        System.out.println(result.val);

    }
    // 使用迭代的思路
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
       while(root != null){
           if(root.val < p.val && root.val < q.val){
               root = root.right;
           }
           else if(root.val > p.val && root.val > q.val){
               root = root.left;
           }else break;
       }
       return root;
    }
    // 使用递归的思路
    public static TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if(root.val < p.val && root.val < q.val){
            return lowestCommonAncestor2(root.right, p,q);
        }
        if(root.val > p.val && root.val > q.val){
            return lowestCommonAncestor2(root.left,p,q);
        }
        return root;
    }
}
