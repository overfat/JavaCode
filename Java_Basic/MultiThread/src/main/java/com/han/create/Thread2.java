package com.han.create;

/**
 * 多线程方法二：通过实现Runnable接口
 * 1。创建一个实现Runnable接口的类
 * 2。实现run方法
 * 3。创建实现类的对象
 * 4。将此对象作为参数传递到Thread类的构造器中，创建Thread类对象
 * 5。通过Thread类对象调用start方法
 */

/**
 * 与继承Thread方法的对比：
 * 开发中：优先选择实现Runnable接口方式
 * 原因： 1。实现的方式没有类的单继承的局限性
 *       2。实现的方式更适合来处理多个线程有共享数据的情况
 *
 * 相同点：都需要实现run方法，将线程要执行的逻辑声明在run方法中
 */

public class Thread2 {
    public static void main(String[] args) {
        // 创建对象
        MyThread test = new MyThread();
        //创建Thread对象
        Thread t1 = new Thread(test);
        t1.setName("线程一：");
        t1.start();

        Thread t2 = new Thread(test);
        t2.setName("线程二：");
        t2.start();
    }
}


class MyThread implements Runnable{

    @Override
    public void run() {
        for(int i = 0; i < 100; i++){
            //打印所有偶数
            if(i % 2 == 0)
                System.out.println(Thread.currentThread().getName() + ": " + i);
        }
    }
}