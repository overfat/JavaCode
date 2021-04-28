package 链表;

/**
 * 输入两个递增排序的链表，合并这两个链表并使新链表中的节点仍然是递增排序的。
 */
public class P25 {
    public static void main(String[] args){
        ListNode n1 = new ListNode(2);
        ListNode n2 = new ListNode(10);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        n1.next = n2;
        n2.next = null;

        n3.next = n4;
        n4.next = null;

        ListNode result = mergeTwoLists(n1, n3);
        while(result != null){
            System.out.println(result.val);
            result = result.next;
        }
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2){
        if(l1 == null){
            return l2;
        }
        if(l2 == null){
            return l1;
        }
        ListNode head;
        if(l1.val < l2.val){
            head = l1;
            l1 = l1.next;
        }else{
            head = l2;
            l2 = l2.next;
        }
        ListNode node2 = head;
        while(l1 != null && l2 != null){
            if(l1.val < l2.val){
                node2.next = l1;
                node2 = node2.next;
                l1 = l1.next;
            }else{
                node2.next = l2;
                node2 = node2.next;
                l2 = l2.next;
            }
        }
        if(l1 == null){
            node2.next = l2;
        }
        if(l2 == null){
            node2.next = l1;
        }
        return head;
    }
}
