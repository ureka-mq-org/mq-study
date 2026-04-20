package com.ureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.ureka")
public class ShsApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShsApplication.class, args);
    }
}
