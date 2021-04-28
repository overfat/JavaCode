package 栈与队列;

import java.util.LinkedList;

/**
 * 定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的 min 函数在该栈中，调用 min、push 及 pop 的时间复杂度都是 O(1)。
 */
public class P30 {
    LinkedList<Integer> stack1;
    LinkedList<Integer> stack2; // 辅助栈，栈顶用来存放栈中最小元素
    public P30(){
        stack1 = new LinkedList<>();
        stack2 = new LinkedList<>();
    }
    public void push(int x){
        stack1.push(x);
        if(stack2.isEmpty() || x < stack2.peek()) stack2.push(x);
        else{
                stack2.push(stack2.peek());
            }
    }
    public void pop(){
        stack1.pop();
        stack2.pop();

    }
    public int top(){
        return stack1.peek();

    }
    public int min(){
        // 返回最小值
        return stack2.peek();
    }
    public static void main(String[] args){
        P30 t1 = new P30();
        t1.push(1);
        t1.push(10);
        System.out.println("栈顶元素： " + t1.top());
        System.out.println("最小值： " + t1.min());
    }
}
