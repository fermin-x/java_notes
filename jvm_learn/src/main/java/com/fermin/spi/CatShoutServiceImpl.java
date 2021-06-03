package com.fermin.spi;

public class CatShoutServiceImpl implements ShoutService {
    @Override
    public void shout() {
        System.out.println("miao~");
    }

    @Override
    public String getCode() {
        return "cat";
    }
}