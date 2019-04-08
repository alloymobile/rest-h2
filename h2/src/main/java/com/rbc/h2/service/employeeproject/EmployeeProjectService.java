package com.rbc.h2.service.employeeproject;

import com.rbc.h2.persistence.dbo.EmployeeProject;
import com.rbc.h2.persistence.repository.EmployeeProjectRepository;
import com.rbc.h2.service.employee.EmployeeService;
import com.rbc.h2.service.project.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeProjectService {

    @Autowired
    EmployeeProjectRepository employeeProjectRepository;

    @Autowired
    ProjectService projectService;

    @Autowired
    EmployeeService employeeService;

    //read all
    public List<EmployeeProjectDTO> readAll(){
        List<EmployeeProjectDTO> employeeProjectDTOList = new ArrayList<>();
        List<EmployeeProject> employeeProject = employeeProjectRepository.findAll();
        for(EmployeeProject d: employeeProject){
            employeeProjectDTOList.add(this.toDTO(d));
        }
        return employeeProjectDTOList;
    }

    //read one
    public EmployeeProjectDTO readById(Long id){
        return this.toDTO(employeeProjectRepository.findById(id).get());
    }

    //addOne
    public EmployeeProjectDTO addOne(EmployeeProjectDTO employeeProjectDTO){
        return this.toDTO(employeeProjectRepository.save(this.toDBO(employeeProjectDTO)));
    }

    //update or add one
    public EmployeeProjectDTO updateOne(Long id,EmployeeProjectDTO employeeProjectDTO){
        if(this.readById(id) != null){
            employeeProjectDTO.setId(id);
        }
        return this.addOne(employeeProjectDTO);
    }

    //delete by id
    public void deleteById(Long id){
        this.employeeProjectRepository.deleteById(id);
    }

    private EmployeeProjectDTO toDTO(EmployeeProject employeeProject){
        EmployeeProjectDTO employeeProjectDTO = new EmployeeProjectDTO();
        employeeProjectDTO.setId(employeeProject.getId());
        employeeProjectDTO.setEmployeId(employeeProject.getEmployee().getId());
        employeeProjectDTO.setProjectId(employeeProject.getProject().getId());
        return employeeProjectDTO;
    }

    private EmployeeProject toDBO(EmployeeProjectDTO employeeProjectDTO){
        EmployeeProject employeeProject = new EmployeeProject();
        if(employeeProjectDTO.getId() != 0){
            employeeProject.setId(employeeProjectDTO.getId());
        }
        employeeProject.setEmployee(employeeService.toDBO(employeeService.readById(employeeProjectDTO.getEmployeId())));
        employeeProject.setProject(projectService.toDBO(projectService.readById(employeeProjectDTO.getProjectId())));
        return employeeProject;
    }
}
