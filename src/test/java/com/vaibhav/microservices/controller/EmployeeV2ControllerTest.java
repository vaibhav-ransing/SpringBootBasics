
package com.vaibhav.microservices.controller;

import com.vaibhav.microservices.model.Employee;
import com.vaibhav.microservices.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(EmployeeV2Controller.class)
public class EmployeeV2ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeV2Controller employeeV2Controller;

    private Employee testEmployee;

    @BeforeEach
    public void setup() {
        testEmployee = new Employee("1", "John Doe", "HR");
    }

    @Test
    public void testSaveEmployee() throws Exception {
        when(employeeService.save(any(Employee.class))).thenReturn(testEmployee);

        mockMvc.perform(MockMvcRequestBuilders.post("/v2/employees/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"employeeId\":\"1\",\"firstName\":\"John Doe\",\"department\":\"HR\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.employeeId").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("John Doe"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.department").value("HR"))
                .andDo(print());
    }

    @Test
    public void testGetAllEmployees() throws Exception {
        List<Employee> employeeList = Arrays.asList(testEmployee);

        when(employeeService.allEmployees()).thenReturn(employeeList);

        mockMvc.perform(MockMvcRequestBuilders.get("/v2/employees/employees"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].employeeId").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].firstName").value("John Doe"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].department").value("HR"))
                .andDo(print());
    }

    @Test
    public void testGetEmployeeById() throws Exception {
        when(employeeService.getEmployeeById(anyString())).thenReturn(testEmployee);

        mockMvc.perform(MockMvcRequestBuilders.get("/v2/employees/get/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.employeeId").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("John Doe"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.department").value("HR"))
                .andDo(print());
    }

    @Test
    public void testDeleteEmployeeById() throws Exception {
        when(employeeService.deleteEmployeeById(anyString())).thenReturn("Employee deleted successfully.");

        mockMvc.perform(MockMvcRequestBuilders.delete("/v2/employees/delete/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Employee deleted successfully."))
                .andDo(print());
    }
}