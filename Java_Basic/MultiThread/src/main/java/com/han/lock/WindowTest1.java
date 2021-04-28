package com.han.lock;

/**
 * 窗口卖票问题:创建三个窗口卖票，总票数为100张，使用实现Runnable的方式
 *
 * 出现的问题：当其中一个线程a在操作车票时，其他的进程参与进来，也来操作车票，因此出现线程问题。
 * 解决：当线程a在操作车票时，其他的线程不可以参与进来，知道线程a操作结束，其他线程才可以操作，这种情况即使线程a出现了阻塞问题，也不能被改变。
 *
 *
 * java中，我们使用方法同步来解决问题
 *
 * 同步方法一：同步代码块
 * synchronized(同步监视器){
 *     //需要被同步的代码
 * }
 *  同步监视器：俗称：锁，任何一个类的对象，都可以充当锁。要求，多个线程必须为同一把锁
 *  补充：在实现Runnable接口创建的多线程方式中，我们可以使用this充当同步监视器
 *
 * 方法二：同步方法
 *  如果操作共享数据的代码完成的声明在一个方法中，我们不妨将此方法声明为同步的
 *
 * 同步的方法：
 *  操作同步代码时，只能有一个线程参与，其他线程等待。相当于是一个单线程的过程，效率低
 */

public class WindowTest1 {
    public static void main(String[] args) {
        Window1 w = new Window1();
        Thread t1 = new Thread(w);
        Thread t2 = new Thread(w);
        Thread t3 = new Thread(w);

        t1.setName("线程一");
        t2.setName("线程二");
        t3.setName("线程三");

        t1.start();
        t2.start();
        t3.start();
    }
}

class Window1 implements Runnable{
    public int ticket = 1000;

    // 重写run方法
    public void run(){
        while(true){
            synchronized(this){ //此时的this指的是唯一的Window1对象
                if(ticket > 0){
//                    try{
//                        Thread.sleep(1000);
//                    }catch (InterruptedException e){
//                        e.printStackTrace();
//                    }
                    System.out.println(Thread.currentThread().getName() + " : " + "票号为： " + ticket);
                    ticket--;
                }else{
                    break;
                }
            }
        }
    }
}
