package com.poc.logging.model;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data

@Getter
@Setter

public class Employee {

    private Integer id;

    private String name;

    private String Email;

    public Employee(Integer id, String name, String email) {
        this.id = id;
        this.name = name;
        Email = email;
    }

}
