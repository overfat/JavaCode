package com.han.lock;

/**
 * 窗口卖票问题，使用继承Thread方法来实现
 */

public class WindowTest2 {
    public static void main(String[] args) {
        Window2 w1 = new Window2();
        Window2 w2 = new Window2();
        Window2 w3 = new Window2();
        w1.start();
        w2.start();
        w3.start();
    }
}

class Window2 extends Thread{
    public static int ticket=100;
    public static Object obj = new Object();

    public void run(){

        while(true){
//            synchronized (Window2.class){ // 这里不可以在用this了，否则指的是三个对象
             synchronized (obj){
                if(ticket > 0){
                    System.out.println(Thread.currentThread().getName() + ": 票号为" + ticket--);
                }else
                    break;
            }
        }
    }
}
