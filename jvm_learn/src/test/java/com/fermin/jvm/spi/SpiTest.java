package com.fermin.jvm.spi;

import com.fermin.spi.ShoutService;
import com.fermin.spi.ShoutServiceManager;

public class SpiTest {

    public static void main(String[] args) {
        ShoutService service = ShoutServiceManager.getInstance().getService("cat");
        service.shout();
        service = ShoutServiceManager.getInstance().getService("dog");
        service.shout();
        service = ShoutServiceManager.getInstance().getService("mow");
        service.shout();
    }

}