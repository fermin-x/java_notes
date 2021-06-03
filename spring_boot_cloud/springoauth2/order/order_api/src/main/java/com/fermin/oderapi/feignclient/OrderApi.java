package com.fermin.oderapi.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * spring.application.name
 */
@Service
@FeignClient(name = "order")
public interface OrderApi {

    @GetMapping(value = "/test1")
    String orderTest1();

    @GetMapping(value = "/test2")
    String orderTest2();

    @GetMapping(value = "/test3")
    String orderTest3();
}
