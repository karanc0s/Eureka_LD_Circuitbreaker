package com.karan.feign_consumer_1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FeignConsumer1Application {

    public static void main(String[] args) {
        SpringApplication.run(FeignConsumer1Application.class, args);
    }

}
