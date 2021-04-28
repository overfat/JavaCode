package com.han.create;

/**
 * 测试线程中常用的方法
 * 1. start() 启动当前线程，调用当前线程的run方法
 * 2. run() 通常需要重写Thread类中的此方法，将创建的线程要执行的操作声明在此方法中
 * 3。currentThread() 静态方法 ： 静态方法，返回执行当前代码的线程
 * 4。getName()
 * 5。setName()
 * 6. yield() 释放当前cpu的执行权
 * 7。stop()方法,已经过时了，强制停止该线程
 * 8。sleep()方法，让当前线程睡多少毫秒
 * 9。isAlive() 判断当前线程是否存活
 *
 * 线程的优先级:
 * MAX_PRIORITY
 * MIN_PRIORITY
 * NORM_PRIORITY 默认优先级
 * 2。如何获取线程的优先级
 *  getPriority()
 *  setPriority()
 */

public class ThreadMethod {
    public static void main(String[] args) {
        HelloThread h1 = new HelloThread();
        //设置线程的优先级
        h1.setPriority(Thread.MAX_PRIORITY);
        h1.start();

        //主线程
        for(int i = 0; i < 100; i++){
            System.out.println(Thread.currentThread().getName() + " : " + i);
        }
    }
}

class HelloThread extends Thread{

    public void run(){
        for(int i = 0; i<100; i++){
            if(i%2 == 0)
                System.out.println(Thread.currentThread().getName() + " : " + i);
        }
    }
}