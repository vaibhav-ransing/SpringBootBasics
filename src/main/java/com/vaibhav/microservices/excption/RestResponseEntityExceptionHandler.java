package com.vaibhav.microservices.excption;

import com.vaibhav.microservices.excption.employee.EmployeeAlreadyPresentException;
import com.vaibhav.microservices.excption.employee.EmployeeNotFoundException;
import com.vaibhav.microservices.model.exceptionModel.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice  // Spring understands that we have to use this class to handle all exception using this class
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EmployeeNotFoundException.class)
    // Whenever the EmployeeNotFoundException occurs it will be directed to this handler automatically by spring
    @ResponseBody  // This exception will return a response body, so we have to annotate it with this.
    @ResponseStatus(HttpStatus.NOT_FOUND) // If not defined by default, it will send it as 200 status code
    public ErrorMessage employeeNotFoundExceptionHandler(EmployeeNotFoundException exception) {
        return new ErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
    }

    @ExceptionHandler(EmployeeAlreadyPresentException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage employeeAlreadyPresentExceptionHandler(EmployeeAlreadyPresentException exception){
        return  new ErrorMessage(HttpStatus.BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage genricException(Exception exception){
        return  new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
    }
}