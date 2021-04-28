package com.hfx.innerClass;

//匿名内部类
public class OuterClass3 {
    public InnerClass3 getInnerClass(final int num, String str2){
        return new InnerClass3() {
            int number = num + 3;
            @Override
            public int getNumber() {
                return number;
            }
        }; // 这里的分号不能省略
    }

    public static void main(String[] args) {
        OuterClass3 out = new OuterClass3();
        InnerClass3 inner = out.getInnerClass(2,"cherry");
        System.out.println(inner.getNumber());
    }
}

interface InnerClass3{
    int getNumber();
}
