package com.fermin.order.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderTestController {

    @GetMapping("/test1")
    @PreAuthorize("hasAuthority('test1')")
    public String test1() {
        return "Hello orderTest test1";
    }

    @GetMapping("/test2")
    @PreAuthorize("hasAuthority('test')")
    public String test2() {
        return "Hello orderTest test2";
    }

    @GetMapping("/test3")
    public String test3() {
        return "Hello orderTest test3";
    }
}
