package com.karan.feign_consumer_2.feign;

import com.karan.feign_consumer_2.model.Employee;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@FeignClient("producer-1")
public interface MyFeignClient {

    @GetMapping(path = "/emp/getDetails" , produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Collection<Employee>> getData();

    @GetMapping("/emp/getDetails/{EmpId}")
    ResponseEntity<Employee> getEmployeeById(@PathVariable("EmpId") Integer id);


    @PostMapping("/emp/addEmployee")
    ResponseEntity<String> addEmployee(
            @RequestBody Employee emp
    );

    @PutMapping("/emp/updateEmployee")
    ResponseEntity<String> updateEmployee(
            @RequestBody Employee emp
    );

    @DeleteMapping("/delete/{EmpId}")
    ResponseEntity<String> deleteEmployee(
            @PathVariable("EmpId") Integer id
    );


}
