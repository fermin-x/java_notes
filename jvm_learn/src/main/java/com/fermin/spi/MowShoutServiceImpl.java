package com.fermin.spi;

public class MowShoutServiceImpl implements ShoutService {
    @Override
    public void shout() {
        System.out.println("mow~");
    }

    @Override
    public String getCode() {
        return "mow";
    }
}