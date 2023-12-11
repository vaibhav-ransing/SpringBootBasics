package com.vaibhav.microservices.serviceTest;

import com.vaibhav.microservices.excption.employee.EmployeeAlreadyPresentException;
import com.vaibhav.microservices.excption.employee.EmployeeNotFoundException;
import com.vaibhav.microservices.model.Employee;
import com.vaibhav.microservices.service.EmployeeV1ServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EmployeeV1ServiceImplTest {

    private EmployeeV1ServiceImpl employeeService;

    @BeforeEach
    public void setUp() {
        employeeService = new EmployeeV1ServiceImpl();
    }

    @Test
    public void saveEmployee_NewEmployee_Success() {
        Employee employee = new Employee();
        employee.setEmployeeId("123");
        employee.setFirstName("John");

        Employee savedEmployee = employeeService.save(employee);
        assertNotNull(savedEmployee);
        assertEquals("123", savedEmployee.getEmployeeId());
        assertEquals("John", savedEmployee.getFirstName());
    }

    @Test
    public void saveEmployee_DuplicateEmployee_ExceptionThrown() {
        Employee employee1 = new Employee();
        employee1.setEmployeeId("123");
        employee1.setFirstName("John");

        employeeService.save(employee1);

        Employee employee2 = new Employee();
        employee2.setEmployeeId("123");
        employee2.setFirstName("Doe");

        assertThrows(EmployeeAlreadyPresentException.class, () -> employeeService.save(employee2));
    }

    @Test
    public void getEmployeeById_ExistingId_ReturnsEmployee() {
        Employee employee = new Employee();
        employee.setEmployeeId("456");
        employee.setFirstName("Jane");

        employeeService.save(employee);

        Employee retrievedEmployee = employeeService.getEmployeeById("456");
        assertNotNull(retrievedEmployee);
        assertEquals("456", retrievedEmployee.getEmployeeId());
        assertEquals("Jane", retrievedEmployee.getFirstName());
    }

    @Test
    public void getEmployeeById_NonExistingId_ExceptionThrown() {
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.getEmployeeById("999"));
    }

    @Test
    public void deleteEmployeeById_ExistingId_EmployeeDeleted() {
        Employee employee = new Employee();
        employee.setEmployeeId("789");
        employee.setFirstName("Jack");

        employeeService.save(employee);

        String result = employeeService.deleteEmployeeById("789");
        assertEquals("Employee Deleted by id 789", result);

        assertTrue(employeeService.allEmployees().isEmpty());
    }

    @Test
    public void deleteEmployeeById_NonExistingId_ExceptionThrown() {
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.deleteEmployeeById("999"));
    }
}
