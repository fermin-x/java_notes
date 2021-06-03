package com.fermin.mallserver.controller;

import com.fermin.oderapi.feignclient.OrderApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MallTestController {

    @Autowired
    private OrderApi orderApi;

    @GetMapping("/test1")
    @PreAuthorize("hasAuthority('test1')")
    public String test1() {
        String result = orderApi.orderTest1();
        return "Hello MallTest test1 " + result;
    }

    @GetMapping("/test2")
    @PreAuthorize("hasAuthority('test')")
    public String test2() {
        String result = orderApi.orderTest2();
        return "Hello MallTest test2 " + result;
    }

    @GetMapping("/test3")
    public String test3() {
        String result = orderApi.orderTest1();
        return "Hello MallTest test3 " + result;
    }
}
