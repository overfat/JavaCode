package 链表;

/**
 * 定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。
 */
public class P24 {
    public static void main(String[] args){
        ListNode n1 = new ListNode(10);
        ListNode n2 = new ListNode(9);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = null;
        ListNode result = reverseList(n1);
        while(result!=null){
            System.out.println(result.val);
            result = result.next;
        }
    }
    public static ListNode reverseList(ListNode head){
        if(head == null || head.next ==  null){
            return head;
        }
        ListNode node = head.next;
        ListNode pre = head;
        pre.next = null;
        while(node != null){
            ListNode next = node.next;
            node.next = pre;
            pre = node;
            node = next;
        }
        return pre;
    }
}
