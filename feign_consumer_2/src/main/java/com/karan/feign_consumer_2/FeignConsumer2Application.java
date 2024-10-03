package com.karan.feign_consumer_2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class FeignConsumer2Application {

    public static void main(String[] args) {
        SpringApplication.run(FeignConsumer2Application.class, args);
    }

}