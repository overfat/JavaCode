package 链表;

/**
 * 输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。
 *
 * 例如，一个链表有 6 个节点，从头节点开始，它们的值依次是 1、2、3、4、5、6。这个链表的倒数第 3 个节点是值为 4 的节点。
 */
public class P22 {
    public static void main(String[] args){
        ListNode n1 = new ListNode(5);
        ListNode n2 = new ListNode(8);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = null;
        ListNode result = getKthFromEnd(n1, 2);
        System.out.println(result.val);
    }
    public static ListNode getKthFromEnd(ListNode head, int k){
        if(head == null){
            return null;
        }
        // 定义一个i
        int i = 1;
        ListNode node = head;
        while(node != null && i < k){
            i++;
            node = node.next;
        }
        ListNode node2 = head;
        while(node.next != null){
            node = node.next;
            node2 = node2.next;
        }
        return node2;
    }
}
