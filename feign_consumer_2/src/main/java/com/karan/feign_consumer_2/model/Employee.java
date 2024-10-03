package com.karan.feign_consumer_2.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    private String employeeName;
    private Integer employeeId;
    private Double employeeSalary;
    private Integer departmentCode;

}
