package com.han.create;

/**
 * 创建线程的方式一：继承Thread类
 * 1.创建一个继承Thread类的子类
 * 2.重写run()方法 -》将此线程执行的操作声明在run中
 * 3。创建Thread类的子类对象
 * 4。通过start方法调用
 */

public class Thread1{
    public static void main(String[] args) {
        //创建对象
        MyThread1 test1 = new MyThread1();
        // 调用start方法开启线程
        test1.start();

        //再次创建一个对象,再次开启一个线程
        MyThread1 test2 = new MyThread1();
        test2.start();


//        for(int i = 0; i < 100; i++){
//            if(i%2 == 1)
//                System.out.println(Thread.currentThread().getName() + " : " +  i);
//        }
    }

}

class MyThread1 extends Thread{
    //重写run方法
    public void run(){
        for(int i = 0; i < 100; i++){
            System.out.println(Thread.currentThread().getName() + " : " + i);
        }
    }
}
