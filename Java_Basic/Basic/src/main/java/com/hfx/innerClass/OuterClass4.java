package com.hfx.innerClass;

public class OuterClass4 {
    private String str;
    public static String name = "fei";

    //静态内部类
    static class InnerClass{
        // 可以存在静态成员
        public static String _name1 = "fei_static";
        public void disPlay(){
            // 静态内部类只能访问外部类的静态成员变量和方法
            System.out.println(name + "  OuterClass");
        }
    }

    class InnerClass2{
        // 非静态内部类中不可以存在静态成员变量
        public String _name = "fei——inner";
        //非静态内部类中可以调用外围类的任何成员，不管是静态的还是非静态的
        public void disPlay(){
            System.out.println(name + " OuterClass");
        }
    }
    public void disPlay(){
        // 外围类访问静态内部类
        System.out.println(InnerClass._name1);
        //静态内部类可以直接创建实例
        new InnerClass().disPlay();
        //非静态内部类的创建需要依靠外围类
        OuterClass4.InnerClass2 inner2 = new OuterClass4().new InnerClass2();
        System.out.println(inner2._name);
        inner2.disPlay();
    }
    public static void main(String[] args){
        OuterClass4 outer = new OuterClass4();
        outer.disPlay();
    }
}
