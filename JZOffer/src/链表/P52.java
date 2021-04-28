package 链表;

/**
 * 输入两个链表，找出它们的第一个公共节点。
 */
public class P52 {
    public static void main(String[] args){
        ListNode2 n1 = new ListNode2(1);
        ListNode2 n2 = new ListNode2(2);
        ListNode2 n3 = new ListNode2(3);
        ListNode2 n4 = new ListNode2(4);
        ListNode2 n5 = new ListNode2(5);
        ListNode2 n6 = new ListNode2(6);
        ListNode2 n7 = new ListNode2(7);
        n1.next = n2;
        n2.next = n6;
        n6.next = n7;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        ListNode2 result = getIntersectionNode(n1, n3);
        System.out.println(result.val);
    }
    public static ListNode2 getIntersectionNode(ListNode2 headA, ListNode2 headB){
        if(headA == null || headB == null){
            return null;
        }
        ListNode2 node1 = headA;
        ListNode2 node2 = headB;
        while(node1 != node2){
            node1 = node1 == null ? headB : node1.next;
            node2 = node2 == null ? headA : node2.next;
        }
        return node1;
    }
}

class ListNode2{
    int val;
    ListNode2 next;
    ListNode2(int val){
        this.val = val;
        this.next = null;
    }
}
