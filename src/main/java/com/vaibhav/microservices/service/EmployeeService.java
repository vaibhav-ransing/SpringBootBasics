package com.vaibhav.microservices.service;

import com.vaibhav.microservices.model.Employee;

import java.util.List;

public interface  EmployeeService  {

    Employee save(Employee employee);

    List<Employee> allEmployees();

    Employee getEmployeeById(String id);

    String deleteEmployeeById(String id);
}
