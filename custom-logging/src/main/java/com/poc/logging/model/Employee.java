package com.poc.logging.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data

@Getter
@Setter
@JsonInclude
public class Employee {

    @JsonProperty
    private Integer id;
    @JsonProperty
    private String  name;
    @JsonProperty
   private String  Email;

    public Employee(Integer id, String name, String email) {
        this.id = id;
        this.name = name;
        Email = email;
    }

    public Employee() {

    }
}
