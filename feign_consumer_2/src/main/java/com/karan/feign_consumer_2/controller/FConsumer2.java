package com.karan.feign_consumer_2.controller;

import com.karan.feign_consumer_2.feign.MyFeignClient;
import com.karan.feign_consumer_2.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@RestController
@RequestMapping("fc2")
public class FConsumer2 {

    @Autowired
    private MyFeignClient feignClient;

    @GetMapping("/data")
    public ResponseEntity<Collection<Employee>> getAllEmployees() {
        return feignClient.getData();
    }

    public ResponseEntity<Employee> getEmployeeById(Integer id){
        return feignClient.getEmployeeById(id);
    }

    public ResponseEntity<String> addEmployee(Employee employee){
        return feignClient.addEmployee(employee);
    }

    public ResponseEntity<String> updateEmployee(Employee employee){
        return feignClient.updateEmployee(employee);
    }

    public ResponseEntity<String> deleteEmployee(Integer id){
        return feignClient.deleteEmployee(id);
    }


    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleException(RuntimeException runtimeException) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(runtimeException.getMessage());
    }


}
