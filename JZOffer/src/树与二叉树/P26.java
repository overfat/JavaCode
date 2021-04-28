package 树与二叉树;

/**
 * 输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)
 * B是A的子结构， 即 A中有出现和B相同的结构和节点值。
 */

public class P26 {
    public static void main(String[] args){

    }
    // 当A为空或者B为空的时候，直接返回false
    // 当B是A的子结构的时候，存在三种情况，B的跟节点和A的根节点相同
    // B是A的左子树的子结构或者B是A的右子树的子结构
    public boolean isSubStructure(TreeNode A, TreeNode B){
        return (A != null && B != null) && (recur(A,B) || isSubStructure(A.left,B) || isSubStructure(A.right, B));
    }

    private static boolean recur(TreeNode A, TreeNode B) {
        if(B == null){
            return true;
        }
        if(A == null || A.val != B.val){
            return false;
        }
        return recur(A.left,B.left) && recur(A.right,B.right);
    }
}
