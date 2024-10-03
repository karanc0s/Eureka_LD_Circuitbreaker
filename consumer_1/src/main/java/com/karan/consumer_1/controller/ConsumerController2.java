package com.karan.consumer_1.controller;

import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@RestController
public class ConsumerController2 {

    @Autowired
    private LoadBalancerClient loadBalancer;


    @GetMapping("/read1")
    public ResponseEntity<String> getEmployees() throws RestClientException , IOException {
        ServiceInstance serviceInstance = loadBalancer.choose("producer_1");
        String baseURL = serviceInstance.getUri().toString();
        baseURL +=  "/emp/get";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response =null;
        try{
            response= restTemplate.getForEntity(baseURL, String.class);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return response;
    }



    private static final String RESILIENCE_INSTANCE_NAME = "jasKeyCompute";
    private ResponseEntity<String> response = null;

    /// with resilieance
    @GetMapping("/read2")
    @CircuitBreaker(
            name = RESILIENCE_INSTANCE_NAME,
            fallbackMethod = "subscribeFallbackMethod"
    )
    public ResponseEntity<String> getData() throws RestClientException , IOException {
        ServiceInstance serviceInstance = loadBalancer.choose("producer_1");
        String baseURL = serviceInstance.getUri().toString();
        baseURL +=  "/emp/get";
        System.out.println("Consumer calling Producer App");
        System.out.println("URL >-> "+baseURL);
        RestTemplate restTemplate = new RestTemplate();
        response = restTemplate.getForEntity(baseURL, String.class);

        return ResponseEntity.ok().body(response.getBody());
    }


    public ResponseEntity<String> subscribeFallbackMethod(HttpServerErrorException.InternalServerError error){
        System.out.println("Producer App responded with Internal Server Error, Generating cached response");
        return ResponseEntity.ok().body(response.getBody());
    }
    public ResponseEntity<String> subscribeFallbackMethod(CallNotPermittedException ex){
        System.out.println("Circuit is in open state, Consumer not calling producer app, generating cached response");
        return ResponseEntity.ok().body(response.getBody());
    }

}
