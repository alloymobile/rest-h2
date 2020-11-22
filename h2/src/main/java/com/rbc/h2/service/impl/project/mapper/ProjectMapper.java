package com.rbc.h2.service.impl.project.mapper;

import com.rbc.h2.exception.NotFoundException;
import com.rbc.h2.persistence.dbo.Project;
import com.rbc.h2.service.impl.employee.EmployeeService;
import com.rbc.h2.service.impl.project.dto.ProjectDTO;
import com.rbc.h2.service.mapper.H2Mapper;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ProjectMapper extends H2Mapper<Project, ProjectDTO> {

    private final EmployeeService employeeService;

    public ProjectMapper(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public void populateDBO(Project dbo, ProjectDTO dto) {
        dbo.setName(dto.getName());
    }

    @Override
    public ProjectDTO toDTOImpl(Project dbo) {
        ProjectDTO dto = new ProjectDTO();
        dto.setId(dbo.getId());
        dto.setName(dbo.getName());
        dto.setEmployees(dbo.getProjectEmployeeList().stream().map(e->this.employeeService.getH2Mapper().toDto(e.getEmployee())).collect(Collectors.toList()));
        return dto;
    }
}

