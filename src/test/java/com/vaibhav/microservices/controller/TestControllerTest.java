package com.vaibhav.microservices.controller;

import com.vaibhav.microservices.model.Employee;
import com.vaibhav.microservices.model.User;
import com.vaibhav.microservices.model.exceptionModel.ErrorMessage;
import com.vaibhav.microservices.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TestControllerUnitTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private TestController testController;

    private MockMvc mockMvc;

    @Test
    public void test() throws Exception {
        Employee employee = new Employee();
        employee.setEmployeeId("123");
        employee.setFirstName("John");
        employee.setDepartment("IT");

        when(employeeService.test(employee)).thenReturn(employee);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/test")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"employeeId\": \"123\", \"firstName\": \"John\", \"department\": \"IT\" }"))
                .andExpect(status().isOk())
                .andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        String responseBody = response.getContentAsString();

        Employee actualEmployee = new ObjectMapper().readValue(responseBody, Employee.class);

        assertEquals(employee, actualEmployee);
    }
}