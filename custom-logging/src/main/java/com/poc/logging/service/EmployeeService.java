package com.poc.logging.service;

import com.poc.logging.model.Employee;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class EmployeeService {

    public List<Employee> getEmployeeData(){
        return Arrays.asList(new Employee(1,"emplyee1","emplyee1@email.com"),
                new Employee(2,"emplyee2","emplyee2@email.com"));
    }


}
