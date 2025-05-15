package com.poc.logging.service;

import com.poc.logging.model.Employee;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class EmployeeService {

    public List<Employee> getEmployeeData(){
        return List.of(
                new Employee(1,"emp1","emp1@gmail.com"),
                new Employee(2,"emp2","emp2@gmail.com")
        );
    }


}
