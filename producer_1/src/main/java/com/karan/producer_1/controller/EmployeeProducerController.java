package com.karan.producer_1.controller;

import com.karan.producer_1.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/emp")
public class EmployeeProducerController {

    @Autowired
    private Environment environment;

    private static Map<Integer , Employee> employees = new LinkedHashMap<>();
    static Integer count = 10003;

    static {
        employees.put(10001,new Employee("Jack" , 10001 , 12345.0 , 5001 ));
        employees.put(10002,new Employee("Justin" , 10002 , 23945.0 , 5002 ));
        employees.put(10003,new Employee("Erik" , 10003 , 16754.0 , 5003 ));
    }

    @GetMapping("/getDetails")
    public ResponseEntity<Collection<Employee>> getEmployees(){
        return new ResponseEntity<Collection<Employee>>(HttpStatus.INTERNAL_SERVER_ERROR);
//        return ResponseEntity.ok(employees.values());
    }


    @GetMapping("/getDetails/{EmpId}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("EmpId") Integer id){
        Employee emp = employees.get(id);
        if(emp!=null){
            return ResponseEntity.ok(emp);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/addEmployee")
    public ResponseEntity<String> addEmployee(
            @RequestBody Employee emp
    ){
        count++;
        emp.setEmployeeId(count);
        employees.put(count,emp);
        return ResponseEntity.status(HttpStatus.CREATED).body("Employee added successfully");
    }

    @PostMapping("/updateEmployee")
    public ResponseEntity<String> updateEmployee(
            @RequestBody Employee emp
    ){
        if(employees.get(emp.getEmployeeId()) == null){
            return ResponseEntity.notFound().build();
        }
        employees.put(emp.getEmployeeId(),emp);
        return ResponseEntity.ok("Employee updated successfully");
    }

    @GetMapping("/delete/{EmpId}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("EmpId") Integer id){
        if(employees.get(id) == null){
            return ResponseEntity.notFound().build();
        }
        employees.remove(id);
        return ResponseEntity.ok("Employee deleted successfully");
    }


    /// this is for checking load balancing on client side
    @GetMapping("/get")
    public ResponseEntity<Collection<Employee>> getDetails(){
        String str = "This Server with portNumber" + environment.getProperty("server.port") + " Got a hit";
        System.out.println(str);
        return ResponseEntity.ok(employees.values());
    }


}
