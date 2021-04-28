package 回溯法;

import 树与二叉树.Test1;
import 树与二叉树.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 输入一棵二叉树和一个整数，打印出二叉树中节点值的和为输入整数的所有路径。从树的根节点开始往下一直到叶节点所经过的节点形成一条路径。
 *
 */
public class P34 {
    static LinkedList<List<Integer>> res = new LinkedList<>();
    static LinkedList<Integer> path = new LinkedList<>();

    public static void main(String[] args) {
        TreeNode node = Test1.buildTree();
        List<List<Integer>> result = pathSum(node, 8);
        for (List<Integer> list : result) {
            for (Integer i : list) {
                System.out.println(i);
            }
        }
    }

    public static List<List<Integer>> pathSum(TreeNode node, int target) {
        int sum = 0;
        List<Integer> list = new ArrayList<>();
        dfs(node, sum, target);
        return res;
    }

    public static void dfs(TreeNode node, int sum, int target) {
        if (node == null) return;
        path.add(node.val);
        sum += node.val;
        if (sum + node.val == target && node.left == null && node.right == null) {
            res.add(new LinkedList<>(path)); // 不要结束
        }
        dfs(node.left, sum, target);
        dfs(node.right, sum, target);
        path.removeLast();
    }
}
