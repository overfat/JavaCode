package com.hfx.basic;

public class People {
    private int age;
    protected String name;
    int score;
    public String love;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

class SmallPeople2{
    public static void main(String[] args) {
        People p1 = new People();
    }
}