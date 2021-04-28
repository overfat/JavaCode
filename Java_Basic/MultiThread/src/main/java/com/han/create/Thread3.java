package com.han.create;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 使用Callable方法创建多线程
 */

public class Thread3 {
    public static void main(String[] args) {
        MyThread3 test = new MyThread3();
        // 将此callable接口实现类的对象作为传递到FutureTask构造器中，创建FutureTask对象
        FutureTask futureTask = new FutureTask(test);
        // 将futureTask的对象作为参数传递到Thread类的构造器中，创建Thread对象，并使用start
        new Thread(futureTask).start();

        Object sum = null;
        try {
            sum = futureTask.get();
            System.out.println("总和： " + sum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}

class MyThread3 implements Callable{
    //实现call方法，将此线程需要执行的操作声明在call中
    public Object call(){
        int sum = 0;
        for(int i = 0; i < 100; i++)
            sum+=i;
        return sum;
    }
}
