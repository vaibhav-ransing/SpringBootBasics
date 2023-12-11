package com.vaibhav.microservices.service;

import com.vaibhav.microservices.excption.employee.EmployeeAlreadyPresentException;
import com.vaibhav.microservices.excption.employee.EmployeeNotFoundException;
import com.vaibhav.microservices.model.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("EmployeeV1ServiceImpl")
public class EmployeeV1ServiceImpl implements EmployeeService {

    List<Employee> empArr = new ArrayList<>();

    @Override
    public Employee save(Employee employee) {

        if (empArr
                .stream()
                .filter(employee1 -> employee1.getEmployeeId()
                        .equals(employee.getEmployeeId()))
                .findFirst()
                .isEmpty()) {
            empArr.add(employee);
        } else {
            throw new EmployeeAlreadyPresentException("Already preset");
        }
        return employee;
    }

    @Override
    public List<Employee> allEmployees() {
        return empArr;
    }

    @Override
    public Employee getEmployeeById(String id) {
        return empArr
                .stream()
                .filter(employee -> employee.getEmployeeId().equalsIgnoreCase(id))
                .findFirst()
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id = " + id));
//                .get()   // (throws exception if data not found)
    }

    @Override
    public String deleteEmployeeById(String id) {
        Employee emp = empArr
                .stream()
                .filter(employee -> employee.getEmployeeId()
                        .equals(id))
                .findFirst()
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id = " + id));
        empArr.remove(emp);
        return "Employee Deleted by id "+ id;
    }
}
