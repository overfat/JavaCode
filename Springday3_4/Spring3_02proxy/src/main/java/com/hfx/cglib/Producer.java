package com.hfx.cglib;

public class Producer {

    public void saleProduct(float money) {
        System.out.println("生产商品并拿到钱 " + money);
    }


    public void afterService(float money) {
        System.out.println("提供售后并拿到钱: " + money);
    }
}
