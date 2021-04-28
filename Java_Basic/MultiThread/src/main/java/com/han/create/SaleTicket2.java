package com.han.create;

public class SaleTicket2 {
    public static void main(String[] args) {
        Window2 w = new Window2();
        Thread t1 = new Thread(w);
        Thread t2 = new Thread(w);
        Thread t3 = new Thread(w);

        t1.start();
        t2.start();
        t3.start();
    }
}

class Window2 extends Thread{
    // 这里需要把值设置为static，确保共享一个值
    private int ticket = 100;

    public void run(){
        while(true)
            if(ticket > 0){
                System.out.println(Thread.currentThread().getName() + ": " + ticket);
                ticket--;
            }else{
                break;
            }
    }
}