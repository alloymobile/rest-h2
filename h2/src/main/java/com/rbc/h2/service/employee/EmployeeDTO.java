package com.rbc.h2.service.employee;


import lombok.Data;

@Data
public class EmployeeDTO {

    private long id;

    private String name;

    private String email;

    private long departmentId;
}
