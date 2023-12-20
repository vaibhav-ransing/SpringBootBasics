package com.vaibhav.microservices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.vaibhav.microservices.model.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(Test.class)
public class TestControllerUnitTest {
    
    @MockBean
    private Test test;
    
    @Autowired
    private MockMvc mockMvc;
    
    @Test
    public void testHome() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Vaibhav"));
    }
    
    @Test
    public void testUser() throws Exception {
        User user = new User();
        user.setEmailId("vaibahv@gmail.com");
        user.setId("41");
        user.setName("Vaibhav Ransing");
        
        Mockito.when(test.user()).thenReturn(user);
        
        mockMvc.perform(MockMvcRequestBuilders.get("/user"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("41"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Vaibhav Ransing"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.emailId").value("vaibahv@gmail.com"));
    }
    
    @Test
    public void testUserById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user/{id}/{id2}", "123", "John"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("user id 123 John"));
    }
}