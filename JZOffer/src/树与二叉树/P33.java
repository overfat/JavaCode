package 树与二叉树;

import java.util.List;

/**
 * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。如果是则返回 true，否则返回 false。假设输入的数组的任意两个数字都互不相同。
 * 二叉搜索树特性，节点的左孩子都比节点小，右孩子都比节点大
 */

public class P33 {

    public static void main(String[] args) {

    }

    public static boolean verifyPostorder(int[] postorder) {
        // 二叉搜索树的后序遍历为 左子树 右子树 根节点
        // 左子树中所有值都小于跟节点，右子树中所有值都大于根节点，我们依据这个来进行判断，当子树中只有一个节点时，返回true
        return recur(postorder, 0, postorder.length - 1);
    }

    private static boolean recur(int[] postorder, int i, int j) {
        if(i >= j) return true;
        int p = i;
        while(postorder[p] < postorder[j]) p++;
        int m = p;
        while(postorder[p] > postorder[j]) p++; //到最后p和j相等
        return p==j && recur(postorder,i,m-1) && recur(postorder,m, j-1);
    }


}
