package com.hfx.proxy;

public class Producer implements IProducer{
    @Override
    public void saleProduct(float money) {
        System.out.println("生产商品并拿到钱 " + money);
    }

    @Override
    public void afterService(float money) {
        System.out.println("提供售后并拿到钱: " + money);
    }
}
