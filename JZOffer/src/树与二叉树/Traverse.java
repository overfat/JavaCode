package 树与二叉树;

import com.sun.source.tree.Tree;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

// 对二叉树进行遍历，前序，中序，后序遍历的递归和非递归写法
public class Traverse {

    @Test
    public void test1(){
        TreeNode node = Test1.buildTree();
//        preTraverRecur(node); //  前序遍历递归
//        preTraverNoRecur(node); // 前序遍历非递归，利用栈的思想，把当前节点入栈，然后依次把栈的右节点和左节点入栈
//        inorderTraverRecur(node);
//        inorderTraverNoRecur(node); // 中序遍历递归
//        postTraverRecur(node); //后序遍历递归
        levelTraver(node);

    }

    // 前序遍历递归写法
    public void preTraverRecur(TreeNode node){
        if(node == null) return;
        System.out.println(node.val);
        preTraverRecur(node.left);
        preTraverRecur(node.right);
    }
    // 前序遍历非递归写法
    public void preTraverNoRecur(TreeNode node){
        Stack<TreeNode> stack = new Stack<TreeNode>();
        if(node != null)
            stack.push(node);
        while(!stack.isEmpty()){
            TreeNode node1 = stack.pop();
            System.out.println(node1.val); //输出节点
            if(node1.right != null){
                stack.push(node1.right);
            }
            if(node1.left != null){
                stack.push(node1.left);
            }
        }
    }

    // 中序遍历递归写法
    public void inorderTraverRecur(TreeNode node){
        if(node == null) return;
        inorderTraverRecur(node.left);
        System.out.println(node.val);
        inorderTraverRecur(node.right);
    }
    // 中序遍历非递归
    public void inorderTraverNoRecur(TreeNode node){

    }
    // 后序遍历
    public void postTraverRecur(TreeNode node){
        if(node == null) return;
        postTraverRecur(node.left);
        postTraverRecur(node.right);
        System.out.println(node.val);
    }

    // 层次遍历
    public void levelTraver(TreeNode node){
        //借助队列的思想
        Queue<TreeNode> queue = new ArrayDeque<>();
        if(node != null)
            queue.add(node);
        while(queue.size() != 0){
            TreeNode node1 = queue.poll();
            System.out.println(node1.val);
            if(node1.left != null){
                queue.add(node1.left);
            }
            if(node1.right != null){
                queue.add(node1.right);
            }
        }
    }

}
