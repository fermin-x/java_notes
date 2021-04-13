package com.fermin.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class OrderApplication {
    public static void main(String[] args) {
        Environment env = SpringApplication.run(OrderApplication.class, args).getEnvironment();
    }

}
