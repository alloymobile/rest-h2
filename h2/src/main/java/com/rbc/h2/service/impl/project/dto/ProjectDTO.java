package com.rbc.h2.service.impl.project.dto;

import com.rbc.h2.service.dto.IH2DTO;
import com.rbc.h2.service.impl.employee.dto.EmployeeDTO;
import lombok.Data;

import java.util.List;

@Data
public class ProjectDTO implements IH2DTO {

    private Long id;

    private String name;

    private List<EmployeeDTO> employees;
}
