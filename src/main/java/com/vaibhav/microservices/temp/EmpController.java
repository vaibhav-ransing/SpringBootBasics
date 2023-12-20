package com.vaibhav.microservices.temp;


import com.vaibhav.microservices.model.Employee;
import com.vaibhav.microservices.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/employees") //, produces = MediaType.APPLICATION_XML_VALUE
public class EmpController {

    @Qualifier("EmployeeV1ServiceImpl")
    @Autowired
    EmployeeService employeeService;

    @PostMapping("/save")
    public Employee save(@RequestBody Employee employee) throws Exception {
        return employeeService.save(employee);
    }

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeService.allEmployees();
    }

    @GetMapping("get/{id}")
    public Employee getEmployeeById(@PathVariable String id) {
        return employeeService.getEmployeeById(id);
    }

    @DeleteMapping(value = "delete/{id}")
    public String deleteEmployeeById(@PathVariable String id){
        return employeeService.deleteEmployeeById(id);
    }

}