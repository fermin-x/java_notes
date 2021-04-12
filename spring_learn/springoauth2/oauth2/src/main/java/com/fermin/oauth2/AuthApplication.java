package com.fermin.oauth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class AuthApplication {
    public static void main(String[] args) {
        Environment env = SpringApplication.run(AuthApplication.class, args).getEnvironment();
    }
}
