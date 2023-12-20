package com.vaibhav.microservices.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaibhav.microservices.model.Employee;
import com.vaibhav.microservices.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmployeeV1Controller.class)
public class EmployeeV1ControllerTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeV1Controller employeeV1Controller;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(employeeV1Controller).build();
    }

    @Test
    public void testSaveEmployee() throws Exception {
        Employee employee = new Employee("1", "John Doe", "IT");

        when(employeeService.save(employee)).thenReturn(employee);

        mockMvc.perform(MockMvcRequestBuilders.post("/v1/employees/save")
                .content(asJsonString(employee))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAllEmployees() throws Exception {
        Employee employee1 = new Employee("1", "John Doe", "IT");
        Employee employee2 = new Employee("2", "Jane Smith", "HR");
        List<Employee> employees = Arrays.asList(employee1, employee2);

        when(employeeService.allEmployees()).thenReturn(employees);

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/employees/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetEmployeeById() throws Exception {
        Employee employee = new Employee("1", "John Doe", "IT");

        when(employeeService.getEmployeeById("1")).thenReturn(employee);

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/employees/get/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteEmployeeById() throws Exception {
        when(employeeService.deleteEmployeeById("1")).thenReturn("Employee deleted successfully");

        mockMvc.perform(MockMvcRequestBuilders.delete("/v1/employees/delete/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    // Helper method to convert object to JSON string
    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}