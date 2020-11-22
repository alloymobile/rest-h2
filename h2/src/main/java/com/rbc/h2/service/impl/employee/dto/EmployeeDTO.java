package com.rbc.h2.service.impl.employee.dto;


import com.rbc.h2.service.dto.IH2DTO;
import com.rbc.h2.service.impl.department.dto.DepartmentDTO;
import lombok.Data;

@Data
public class EmployeeDTO implements IH2DTO {

    private Long id;

    private String name;

    private String email;

    private DepartmentDTO department;
}
