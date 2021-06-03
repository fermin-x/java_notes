package com.fermin.spi;

public class DogShoutServiceImpl implements ShoutService {
    @Override
    public void shout() {
        System.out.println("wang!");
    }

    @Override
    public String getCode() {
        return "dog";
    }
}