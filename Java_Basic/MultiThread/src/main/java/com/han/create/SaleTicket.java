package com.han.create;

/**
 * 此时的卖票问题还有多线程的问题没有解决
 */

public class SaleTicket {
    public static void main(String[] args) {
        Window w1 = new Window();
        Window w2 = new Window();
        Window w3 = new Window();
        w1.setName("窗口1 ");
        w2.setName("窗口2 ");
        w3.setName("窗口3 ");

        w1.start();
        w2.start();
        w3.start();
    }
}

class Window extends Thread{
    // 这里需要把值设置为static，确保共享一个值
    private static int ticket = 100;

    public void run(){
        while(true)
            if(ticket > 0){
                System.out.println(getName() + ": " + ticket);
                ticket--;
            }else{
                break;
            }
    }
}
