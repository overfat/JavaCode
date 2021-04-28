package 栈与队列;

import java.util.LinkedList;
import java.util.Stack;

/**
 * 用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，
 * 分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，deleteHead操作返回 -1 )
 */
public class P09 {
    LinkedList<Integer> stack1;
    LinkedList<Integer> stack2;
    public P09(){
        stack1 = new LinkedList<>();
        stack2 = new LinkedList<>();
    }

    public void appendTail(int value){
        // 进队列,使用s1进，s2出
        stack1.push(value);

    }
    public int deleteHead(){
        // 删除元素
        if(stack2.isEmpty()){
            if(stack1.isEmpty()) return -1;
            else{
                while(!stack1.isEmpty()){
                    stack2.push(stack1.pop());
                }
                return stack2.pop();
            }
        }else{
            return stack2.pop();
        }
    }
    public static void main(String[] args){
        Stack<Integer> s1 = new Stack<Integer>();
        s1.push(1);
        s1.push(2);
        s1.push(3);
        int value = s1.pop();
        System.out.println(value);
    }
}
