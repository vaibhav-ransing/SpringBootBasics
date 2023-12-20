package com.vaibhav.microservices.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonIgnoreProperties({"department"})
public class Employee {

    private String employeeId;
    private String firstName;
    private String department;


}