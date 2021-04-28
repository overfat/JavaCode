package com.hfx.innerClass;
// 成员内部类
public class OutClass1 {
    private String name;
    public void disPlay(){
        System.out.println(name + "  outerClass");
    }

    //成员内部类
    public class InnerClass{
        public void innerDisPlay(){
            //使用外围的属性
            name = "innerClass ...";
            // 使用外围的方法
            disPlay();
        }
    }
    // 获取内部类成员
    public InnerClass getInnerClass(){
        return new InnerClass();
    }

    public static void main(String[] args) {
        OutClass1 out = new OutClass1();
        OutClass1.InnerClass inner = out.new InnerClass();
        inner.innerDisPlay();
    }
}
