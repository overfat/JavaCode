package 数学;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 0,1,···,n-1这n个数字排成一个圆圈，从数字0开始，每次从这个圆圈里删除第m个数字（删除后从下一个数字开始计数）。求出这个圆圈里剩下的最后一个数字。
 */

public class P62 {
    public static void main(String[] args) {
        int res = lastRemaining(5,3);
        System.out.println(res);
    }
    public static int lastRemaining(int n, int m) {
        // 由当前位置找到下一个位置，当前位置为ids,下一个位置为(ids + m - 1)%n
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i<n; i++)
            list.add(i);
        int ids = 0;
        while(n > 1) {
            ids = (ids + m - 1) % n;
            list.remove(ids);
            n--;
        }
        return list.get(0);
    }
}
