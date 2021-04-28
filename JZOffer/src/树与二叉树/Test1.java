package 树与二叉树;

// 本题中讲述主要写二叉树的基本遍历问题

import com.sun.source.tree.Tree;

public class Test1 {

    public static void main(String[] args){
        TreeNode root = buildTree();
//        preOrder(root);
        inOrder(root);
    }

    public static TreeNode buildTree(){
        //构造基本的二叉树
        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(8);
        TreeNode node9 = new TreeNode(9);
        TreeNode node10 = new TreeNode(10);
        TreeNode node11 = new TreeNode(11);

        root.left = node2;
        root.right = node3;

        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
        node4.right = node8;
        node5.left = node9;
        node6.right = node10;
        node7.left = node11;
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
    //  中序遍历
    public static void inOrder(TreeNode root){
        if(root.left != null)
            inOrder(root.left);
        System.out.println(root.val);
        if(root.right != null)
            inOrder(root.right);
    }

}


