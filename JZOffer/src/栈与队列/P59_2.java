package 栈与队列;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 请定义一个队列并实现函数 max_value 得到队列里的最大值，要求函数max_value、push_back 和 pop_front 的均摊时间复杂度都是O(1)。
 */
public class P59_2 {
    Deque<Integer> d1;
    Deque<Integer> d2; //辅助队列
    public P59_2(){
        d1 = new ArrayDeque<>();
        d2 = new ArrayDeque<>();
    }
    public int max_value(){
        if(d1.isEmpty()) return -1;
        return d2.peek();
    }
    public void push_back(int value){
        d1.offer(value);
        if(!d2.isEmpty() && value > d2.peek()){
            d2.pollLast();
        }
        d2.offer(value);

    }
    public int pop_front(){
        if(d1.isEmpty()) return -1;
        int val = d1.pop();
        if(val == d2.peek())
            d2.pop();
        return val;
    }
    public static void main(String[] args){
        P59_2 test = new P59_2();
        test.push_back(1);
        test.push_back(2);
        test.push_back(5);
        test.push_back(3);
        test.push_back(4);

        test.pop_front();
        test.pop_front();
        test.pop_front();
        System.out.println(test.max_value());
        test.pop_front();
    }
}
