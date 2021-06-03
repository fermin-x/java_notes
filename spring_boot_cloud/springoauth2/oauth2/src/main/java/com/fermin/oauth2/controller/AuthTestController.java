package com.fermin.oauth2.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthTestController {

    @GetMapping("/test1")
    @PreAuthorize("hasAuthority('test1')")
    public String test1() {
        return "Hello test1";
    }

    @GetMapping("/test2")
    @PreAuthorize("hasAuthority('test')")
    public String test2() {
        return "Hello test2";
    }

    @GetMapping("/test3")
    public String test3() {
        return "Hello test3";
    }
}
