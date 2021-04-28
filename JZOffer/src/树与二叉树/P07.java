package 树与二叉树;

import java.util.HashMap;

/**
 * 输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 *
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 */

public class P07 {
    private static HashMap<Integer,Integer> hashMap;
    public static void main(String[] args) {
        int[] preOrder = new int[]{3,9,20,15,7};
        int[] inorder = new int[]{9,3,15,20,7};
        TreeNode root = buildTree(preOrder, inorder);
        preOrder(root);

    }
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        // 需要记录中序遍历中的每一个值的位置
        hashMap = new HashMap<>();
        int n = preorder.length;
        for(int i = 0; i < n; i++){
            hashMap.put(inorder[i], i);
        }
        return reBuildTree(preorder, inorder, 0, n-1, 0, n-1);

    }

    private static TreeNode reBuildTree(int[] preorder, int[] inorder, int i, int i1, int i2, int i3) {
        // 这里需要一个判断终止的条件
        if(i > i1){
            return null;
        }
        // 首先需要找到根节点
        TreeNode root = new TreeNode(preorder[i]);
        // 找到根节点在中序遍历的索引
        int index = hashMap.get(preorder[i]);
        //找到中间有几个节点
        int length = index - i2;
        //构建左边的树
        root.left = reBuildTree(preorder, inorder, i+1,i+length, i2, i2+length-1);
        // 构建右边的树
        root.right = reBuildTree(preorder,inorder,i+length+1,i1, i2+length+1, i3);
        // 完毕之后
        return root;

    }

    public static void preOrder(TreeNode root){
        if(root != null){
            System.out.println(root.val);
        }
        if(root.left != null)
            preOrder(root.left);
        if(root.right != null)
            preOrder(root.right);
    }
}
