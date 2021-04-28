package com.hfx.service.impl;

import com.hfx.service.IAccountService;

import java.util.Date;

public class AccountServiceImpl1 implements IAccountService {
    // 如果是经常变化的数据，不适合使用注入的方式
    private String name;
    private Integer age;
    private Date birthday;

    public AccountServiceImpl1(String name, Integer age, Date birthday){
        this.name = name;
        this.age = age;
        this.birthday = birthday;
    }

    public void saveAccount(){
        System.out.println("saveAccount方法执行了" + name + " " +  age + " " + birthday);
    }
}
