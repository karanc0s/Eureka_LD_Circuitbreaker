package com.karan.consumer_1.model;


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
