package com.vaibhav.microservices.temp;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import com.vaibhav.microservices.temp.EmpController;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import com.vaibhav.microservices.model.Employee;
import com.vaibhav.microservices.model.exceptionModel.ErrorMessage;
import com.vaibhav.microservices.service.EmployeeService;

public class EmpControllerTest {

    private EmpController empController;
    private EmployeeService employeeService;

    @Before
    public void setup() {
        employeeService = mock(EmployeeService.class);
        empController = new EmpController();
        empController.employeeService = employeeService;
    }

    @Test
    public void testSaveEmployee() throws Exception {
        // Arrange
        Employee employee = new Employee("1", "John Doe", "IT");
        when(employeeService.save(any(Employee.class))).thenReturn(employee);
        
        // Act
        Employee savedEmployee = empController.save(employee);
        
        // Assert
        assertNotNull(savedEmployee);
        assertEquals("1", savedEmployee.getEmployeeId());
        assertEquals("John Doe", savedEmployee.getFirstName());
        assertEquals("IT", savedEmployee.getDepartment());
    }

    @Test
    public void testGetAllEmployees() {
        // Arrange
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("1", "John Doe", "IT"));
        employees.add(new Employee("2", "Jane Smith", "HR"));
        when(employeeService.allEmployees()).thenReturn(employees);
        
        // Act
        List<Employee> allEmployees = empController.getAllEmployees();
        
        // Assert
        assertNotNull(allEmployees);
        assertEquals(2, allEmployees.size());
    }

    @Test
    public void testGetEmployeeById() {
        // Arrange
        Employee employee = new Employee("1", "John Doe", "IT");
        when(employeeService.getEmployeeById("1")).thenReturn(employee);
        
        // Act
        Employee retrievedEmployee = empController.getEmployeeById("1");
        
        // Assert
        assertNotNull(retrievedEmployee);
        assertEquals("1", retrievedEmployee.getEmployeeId());
        assertEquals("John Doe", retrievedEmployee.getFirstName());
        assertEquals("IT", retrievedEmployee.getDepartment());
    }

    @Test
    public void testDeleteEmployeeById() {
        // Arrange
        String id = "1";
        when(employeeService.deleteEmployeeById("1")).thenReturn("Employee deleted successfully");
        
        // Act
        String result = empController.deleteEmployeeById("1");
        
        // Assert
        assertNotNull(result);
        assertEquals("Employee deleted successfully", result);
    }
}