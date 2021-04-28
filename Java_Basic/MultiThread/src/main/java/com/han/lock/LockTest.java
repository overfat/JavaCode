package com.han.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 解决线程安全问题三： Lock锁
 *
 * 面试题：synchronized和Lock的区别
 * 相同：二者都可以解决线程安全问题
 * 不同点：synchronized机制在执行完相应同步的代码之后，会自动释放锁
 * 但是Lock需要手动的启动同步，同时结束后也需要手动的释放
 */

public class LockTest {
    public static void main(String[] args) {
        Window w1 = new Window();

        Thread t1 = new Thread(w1);
        Thread t2 = new Thread(w1);
        Thread t3 = new Thread(w1);

        t1.start();
        t2.start();
        t3.start();


    }
}

class Window implements Runnable{
    private int ticket = 100;
    //实例化ReentrantLock
    private ReentrantLock lock = new ReentrantLock();
    public void run(){
        while(true){
            try{
                //调用锁定方法
                lock.lock();
                if(ticket > 0){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " 售票，票号为: " + ticket--);
                }else
                    break;
            }finally {
                lock.unlock();
            }
        }
    }
}