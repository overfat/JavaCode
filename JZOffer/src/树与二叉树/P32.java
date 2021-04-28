package 树与二叉树;

// 从上到下打印出二叉树的每个节点，同一层的节点按照从左到右的顺序打印。

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class P32 {
    public static void main(String[] args) {
        TreeNode node = Test1.buildTree();
//        int[] result = levelOrder(node);
//        List<List<Integer>> list = levelOrder2(node);
        List<List<Integer>> list = levelOrder3(node);
        for(List<Integer> i : list){
            for(Integer i2 : i){
                System.out.print(i2 + " ");
            }
            System.out.println();
        }
    }

    public static int[] levelOrder(TreeNode root){
        // 类似于层次遍历
        if(root == null){
            return new int[0];
        }
        // 如果不为空的话
        ArrayList<Integer> list = new ArrayList<Integer>();
        // 构建一个队列
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while(!queue.isEmpty()){
            TreeNode temp = queue.poll();
            list.add(temp.val);
            if(temp.left != null){
                queue.add(temp.left);
            }
            if(temp.right != null){
                queue.add(temp.right);
            }
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    // 从上到下按层打印二叉树，同一层的节点按从左到右的顺序打印，每一层打印到一行。
    public static List<List<Integer>> levelOrder2(TreeNode root) {
        //设置一个count，记录每层加入的个树
        if(root == null){
            return new ArrayList<>();
        }
        List<List<Integer>> all_list = new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        int count = 1;
        int number = 0;
        while(!queue.isEmpty()){
            // 弹出元素
            List<Integer> list = new ArrayList<>();
            for(int i = count; i>0;i--){
                TreeNode temp = queue.poll();
                list.add(temp.val);
                if(temp.left != null){
                    queue.add(temp.left);
                    number++;
                }
                if(temp.right != null){
                    queue.add(temp.right);
                    number++;
                }
            }
            all_list.add(list);
            count = number;
            number = 0;
        }
        return all_list;

    }
    // 请实现一个函数按照之字形顺序打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右到左的顺序打印，
    // 第三行再按照从左到右的顺序打印，其他行以此类推。
    public static List<List<Integer>> levelOrder3(TreeNode root){
        // 如果顶点为空的话，返回null
        if(root == null){
            return new ArrayList<>();
        }
        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        int count = 1;
        int number = 0;
        while(!queue.isEmpty()){
            // 弹出元素
            List<Integer> list = new ArrayList<>();
            for(int i = count; i>0;i--){
                TreeNode temp = queue.poll();
                list.add(temp.val);
                if(temp.left != null){
                    queue.add(temp.left);
                    number++;
                }
                if(temp.right != null){
                    queue.add(temp.right);
                    number++;
                }
            }
            // 把list反转一个即可
            List<Integer> list2 = new ArrayList<>();
            for(Integer i : list){
                list2.add(0,i);
            }
            result.add(list2);
            count = number;
            number = 0;
        }
        return result;
    }
}
