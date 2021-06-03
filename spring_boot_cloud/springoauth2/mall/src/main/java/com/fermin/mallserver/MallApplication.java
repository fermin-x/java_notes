package com.fermin.mallserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.core.env.ConfigurableEnvironment;

@SpringBootApplication(scanBasePackages = {"com.fermin"})
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.fermin"})
public class MallApplication {
    public static void main(String[] args) {
        ConfigurableEnvironment environment = SpringApplication.run(MallApplication.class, args).getEnvironment();
    }
}


