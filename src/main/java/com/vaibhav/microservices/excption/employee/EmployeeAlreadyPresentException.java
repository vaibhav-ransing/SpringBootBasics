package com.vaibhav.microservices.excption.employee;

public class EmployeeAlreadyPresentException extends RuntimeException{

    public EmployeeAlreadyPresentException(String message){
        super(message);
    }
}
