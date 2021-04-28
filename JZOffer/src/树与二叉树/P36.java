package 树与二叉树;

/**
 * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的循环双向链表。要求不能创建任何新的节点，只能调整树中节点指针的指向。
 */

public class P36 {
    public  static Node head,pre;
    public static void main(String[] args) {
        Node node1 = new Node(4);
        Node node2 = new Node(2);
        Node node3 = new Node(1);
        Node node4 = new Node(3);
        Node node5 = new Node(5);
        node1.left = node2;
        node1.right = node5;
        node2.left = node3;
        node2.right = node4;
        Node res = treeToDoublyList(node1);
        for(int i = 0; i < 20; i++){
            if(res.right != null){
                System.out.println(res.val);
                res = res.right;
            }
        }

    }
    public static Node treeToDoublyList(Node root) {
        if(root == null){
            return null;
        }
        recur(root);
        head.left = pre;
        pre.right = head;
        return head;

    }
    public static void recur(Node root){
        if(root == null) return;
        recur(root.left);

        if(pre != null) pre.right = root;
        else head = root;
        root.left = pre;
        pre = root;

        recur(root.right);

    }
}

class Node{
    public int val;
    public Node left;
    public Node right;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val,Node _left,Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
}