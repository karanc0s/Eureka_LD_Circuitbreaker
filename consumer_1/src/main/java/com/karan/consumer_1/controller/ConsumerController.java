package com.karan.consumer_1.controller;



import com.fasterxml.jackson.databind.ObjectMapper;
import com.karan.consumer_1.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@RestController
@RequestMapping("/emp")
public class ConsumerController {

    @Autowired
    private DiscoveryClient discoveryClient;


    @GetMapping("/all")
    public ResponseEntity<Collection<Employee>> getAllEmployees() {
        List<ServiceInstance> instances = discoveryClient.getInstances("producer_1");
        ServiceInstance serviceInstance = instances.get(0);

        instances.forEach(System.out::println);

        String baseURL = serviceInstance.getUri().toString();
        baseURL +=  "/emp/getDetails";
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<List> data = restTemplate.exchange(baseURL , HttpMethod.GET , null , List.class);

        List<LinkedHashMap<String, Object>> employees =  data.getBody();


        ObjectMapper mapper = new ObjectMapper();
        Collection<Employee> list = new ArrayList<>();

        if(employees != null){

            for(LinkedHashMap<String , Object> map : employees){
                Employee emp = mapper.convertValue(map , Employee.class);
                list.add(emp);
            }

        }

        return new ResponseEntity<Collection<Employee>>(list , data.getStatusCode());

    }



}
