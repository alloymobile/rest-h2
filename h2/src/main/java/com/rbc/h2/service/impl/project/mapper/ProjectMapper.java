package com.rbc.h2.service.impl.project.mapper;

import com.rbc.h2.exception.NotFoundException;
import com.rbc.h2.persistence.dbo.Employee;
import com.rbc.h2.persistence.dbo.EmployeeProject;
import com.rbc.h2.persistence.dbo.Project;
import com.rbc.h2.persistence.repository.EmployeeProjectRepository;
import com.rbc.h2.service.impl.employee.EmployeeService;
import com.rbc.h2.service.impl.employee.dto.EmployeeDTO;
import com.rbc.h2.service.impl.employeeproject.EmployeeProjectService;
import com.rbc.h2.service.impl.project.ProjectService;
import com.rbc.h2.service.impl.project.dto.ProjectDTO;
import com.rbc.h2.service.mapper.H2Mapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ProjectMapper extends H2Mapper<Project, ProjectDTO> {

    private final EmployeeService employeeService;
    private final EmployeeProjectService employeeProjectService;


    public ProjectMapper(EmployeeService employeeService,EmployeeProjectService employeeProjectService) {
        this.employeeService = employeeService;
        this.employeeProjectService = employeeProjectService;
    }

    @Override
    public void populateDBO(Project dbo, ProjectDTO dto) {
        if(dto.getEmployees() == null){
            throw new NotFoundException("Employees can not be null");
        }
        dbo.setName(dto.getName());
        //Only used in case of update
        if(dbo.getId() != null){
            List<EmployeeProject> employeeProjectList = new ArrayList<>();
            for(EmployeeProject employeeProject: dbo.getProjectEmployeeList()){
                Optional<EmployeeDTO> emp = dto.getEmployees().stream().filter(e->e.getId().equals(employeeProject.getEmployee().getId())).findFirst();
                if(!emp.isPresent()){
                    employeeProject.setProject(null);
                    this.employeeProjectService.delete(employeeProject);
                }else{
                    employeeProjectList.add(employeeProject);
                }
            }
            for(EmployeeDTO e: dto.getEmployees()){
                Optional<EmployeeProject> employeeProject = dbo.getProjectEmployeeList().stream().filter(p->p.getEmployee().getId().equals(e.getId())).findFirst();
                if(!employeeProject.isPresent()){
                    EmployeeProject empProject = new EmployeeProject();
                    empProject.setProject(dbo);
                    this.employeeService.findById(e.getId()).ifPresent(employee -> empProject.setEmployee(employee));
                    this.employeeProjectService.create(empProject).ifPresent(employeeProject1 -> employeeProjectList.add(employeeProject1));
                }
            }
            dbo.setProjectEmployeeList(employeeProjectList);
        }
    }

    @Override
    public ProjectDTO toDTOImpl(Project dbo) {
        ProjectDTO dto = new ProjectDTO();
        dto.setId(dbo.getId());
        dto.setName(dbo.getName());
        if(dbo.getProjectEmployeeList() != null){
            dto.setEmployees(dbo.getProjectEmployeeList().stream().map(e->this.employeeService.getH2Mapper().toDto(e.getEmployee())).collect(Collectors.toList()));
        }
        return dto;
    }
}

