package com.fermin.spi;

/**
 * 具体第三方服务的管理类，
 * 可以通过泛型，或者制定类进行
 */
public class ShoutServiceManager extends AbstractThirdService<ShoutService>{

    private ShoutServiceManager(){}

    private static class Singleton {
        static final ShoutServiceManager INSTANCE = new ShoutServiceManager();
    }

    public static ShoutServiceManager getInstance() {
        return Singleton.INSTANCE;
    }
}