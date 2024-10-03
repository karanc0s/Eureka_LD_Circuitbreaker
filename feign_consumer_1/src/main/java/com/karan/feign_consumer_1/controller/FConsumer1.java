package com.karan.feign_consumer_1.controller;

import com.karan.feign_consumer_1.feign.EmpFeignClient;
import com.karan.feign_consumer_1.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("fc1")
public class FConsumer1 {

    @Autowired
    private EmpFeignClient feignClient;


    @GetMapping("/getAll")
    public ResponseEntity<Collection<Employee>> getAllEmployees() {
        return feignClient.getEmployees();
    }

}
