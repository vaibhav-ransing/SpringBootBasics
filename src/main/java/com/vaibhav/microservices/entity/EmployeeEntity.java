package com.vaibhav.microservices.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_employee")  // if not provided it creates table name based on class name i.e EmployeeEntity
public class EmployeeEntity{

    @Id
    private String employeeId;
    private String firstName;
    //    @JsonIgnore
    private String department;
}
