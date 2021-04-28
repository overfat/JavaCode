package 链表;

/**
 * 请实现 copyRandomList 函数，复制一个复杂链表。在复杂链表中，每个节点除了有一个 next 指针指向下一个节点，
 * 还有一个 random 指针指向链表中的任意节点或者 null。
 */

public class P35 {
    public static void main(String[] args){
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        // random

        n1.random = null;
        n2.random = n4;
        n3.random = n1;
        n4.random = n2;
        n5.random = n3;

        Node result = copyRandomList(n1);
        System.out.println(result.val);
    }
    public static Node copyRandomList(Node head){
        // 首先需要把复制的节点放在原来节点后面
        if(head == null){
            return null;
        }
        // 复制节点
        for(Node node = head, copy = null; node != null; node = node.next){
            copy = new Node(node.val);
            copy.next = node.next;
            node.next = copy;
        }
        // 复制random节点
        for(Node node = head; node != null; node = node.next.next){
            if(node.random != null)
                node.next.random = node.random.next;
        }
        // 把一个链表一分为二
        Node newHead = head.next;
        for(Node node = head,temp = null; node!=null && node.next != null;){
            temp = node.next;
            node.next = temp.next;
            node = temp;
        }
        return newHead;

    }
}

class Node{
    int val;
    Node next;
    Node random;

    public Node(int val){
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
