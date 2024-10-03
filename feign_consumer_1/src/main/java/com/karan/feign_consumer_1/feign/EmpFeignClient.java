package com.karan.feign_consumer_1.feign;


import com.karan.feign_consumer_1.model.Employee;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@FeignClient(
        name = "emp",
        url = "http://localhost:8005/emp/"
)
public interface EmpFeignClient {

    @GetMapping("/getDetails")
    ResponseEntity<Collection<Employee>> getEmployees();

    @GetMapping("/getDetails/{EmpId}")
    ResponseEntity<Employee> getEmployeeById(@PathVariable("EmpId") Integer id);


    @PostMapping("/addEmployee")
    ResponseEntity<String> addEmployee(
            @RequestBody Employee emp
    );

    @PutMapping("/updateEmployee")
    ResponseEntity<String> updateEmployee(
            @RequestBody Employee emp
    );

    @DeleteMapping("/delete/{EmpId}")
    ResponseEntity<String> deleteEmployee(
            @PathVariable("EmpId") Integer id
    );

}
