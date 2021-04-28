package 链表;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 输入链表的一个头节点，从尾到头反过来返回每个节点的值
 */

public class P06 {
    public static void main(String[] args){
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = null;
        int[] result = reversePrint2(n1);
        for(int i : result){
            System.out.println(i);
        }
    }
    public static int[] reversePrint(ListNode head){
        // 遍历链表，把值存放在栈中
        if(head == null){
            return new int[0];
        }
        LinkedList<Integer> list = new LinkedList<Integer>();
        while(head != null){
            list.addFirst(head.val);
            head = head.next;
        }
        int[] res = new int[list.size()];
        int i = 0;
        for(int val:list){
            res[i++] = val;
        }
        return res;

    }
    public static int[] reversePrint2(ListNode head){
        ListNode node = head;
        int count = 0;
        while(node != null){
            count++;
            node = node.next;
        }
        int[] result = new int[count];
        node = head;
        for(int i = count-1; i>=0; i--){
            result[i] = node.val;
            node = node.next;
        }
        return result;
    }
}

class ListNode{
    int val;
    ListNode next;
    ListNode(int val){
        this.val = val;
    }
}
