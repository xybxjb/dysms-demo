package com.julings.consumerfeigndemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = {"com.julings.consumerfeigndemo.service"})
public class ConsumerFeignDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerFeignDemoApplication.class, args);
    }

}