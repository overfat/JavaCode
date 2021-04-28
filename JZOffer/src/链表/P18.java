package 链表;

/**
 * 给定单向链表的头指针和一个要删除的节点的值，定义一个函数删除该节点。
 * 返回删除后的链表的头节点。
 */

public class P18 {
    public static void main(String[] args){
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = null;
        ListNode result = deleteNode(n1, 3);
        while(result != null){
            System.out.println(result.val);
            result = result.next;
        }
    }

    public static ListNode deleteNode(ListNode head, int val){
        // 判断是否为空
        if(head == null){
            return null;
        }
        if(head.val == val){
            return head.next;
        }
        ListNode node = head.next;
        ListNode pre = head;
        while(node != null){
            if(node.val == val){
                pre.next = node.next;
                break;
            }
            node = node.next;
            pre = pre.next;
        }
        return head;

    }
}
