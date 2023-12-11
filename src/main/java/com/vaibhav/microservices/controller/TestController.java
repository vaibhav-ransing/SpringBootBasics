package com.vaibhav.microservices.controller;

import com.vaibhav.microservices.model.Employee;
import com.vaibhav.microservices.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Qualifier("EmployeeV1ServiceImpl")
    @Autowired
    EmployeeService employeeService;

    @PostMapping("/test")
    public Employee test(@RequestBody Employee emp){
        return emp;
    }
}
