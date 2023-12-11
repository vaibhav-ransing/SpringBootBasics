package com.vaibhav.microservices.service;

import com.vaibhav.microservices.entity.EmployeeEntity;
import com.vaibhav.microservices.model.Employee;
import com.vaibhav.microservices.repository.EmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service("EmployeeV2ServiceImpl")
public class EmployeeV2ServiceImpl implements EmployeeService{

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public Employee save(Employee employee) {

        if(employee.getEmployeeId() == null || employee.getEmployeeId().isEmpty()){
            employee.setEmployeeId(UUID.randomUUID().toString());
        }
        EmployeeEntity employeeEntity = new EmployeeEntity();
        BeanUtils.copyProperties(employee, employeeEntity);
        employeeRepository.save(employeeEntity);
        return employee;
    }

    @Override
    public List<Employee> allEmployees() {
        List<EmployeeEntity> employeeEntitiesList = employeeRepository.findAll();
        List<Employee> employees = employeeEntitiesList.stream().map(employeeEntity -> {
            Employee employee = new Employee();
            BeanUtils.copyProperties(employeeEntity, employee);
            return  employee;
        }).collect(Collectors.toList());
        return  employees;
    }

    @Override
    public Employee getEmployeeById(String id) {
        EmployeeEntity employeeEntity =  employeeRepository.getReferenceById(id);
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeEntity, employee);
        return  employee;
    }

    @Override
    public String deleteEmployeeById(String id) {
        employeeRepository.deleteById(id);
        return "Delete Employee with Id"+ id;
    }
}
