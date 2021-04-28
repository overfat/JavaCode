package com.hfx.basic;

public class SmallPeople extends People{

    public static void main(String[] args) {
        People p1 = new People();
        SmallPeople s1 = new SmallPeople();
        //此时s1是同一个包中的子类，其可以访问protected,public和default的成员变量
        p1.setAge(10);
        p1.name = "fei";
        System.out.println(s1.name);
        System.out.println(p1.getAge());
        System.out.println(s1.getAge());
    }
}
