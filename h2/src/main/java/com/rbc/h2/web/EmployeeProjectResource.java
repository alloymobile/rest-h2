package com.rbc.h2.web;


import com.rbc.h2.persistence.dbo.EmployeeProject;
import com.rbc.h2.service.employeeproject.EmployeeProjectDTO;
import com.rbc.h2.service.employeeproject.EmployeeProjectService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "{EmployeeProject}")
@RequestMapping("employeeProject")
public class EmployeeProjectResource {

    @Autowired
    EmployeeProjectService employeeProjectService;

    @GetMapping(value = "/{employeeProjectId}", produces = "application/json")
    public EmployeeProjectDTO getEmployeeProject(@PathVariable(name="employeeProjectId")Long employeeProjectId) {
        return employeeProjectService.readById(employeeProjectId);
    }

    @GetMapping(value = "", produces = "application/json")
    public List<EmployeeProjectDTO> getEmployeeProjects() {
        return employeeProjectService.readAll();
    }

    @PostMapping(value = "", produces = "application/json")
    public EmployeeProjectDTO saveEmployeeProject(@RequestBody EmployeeProjectDTO employeeProjectDTO){
        return employeeProjectService.addOne(employeeProjectDTO);
    }

    @DeleteMapping(value = "/{employeeProjectId}", produces = "application/json")
    public void deleteEmployeeProject(@PathVariable(name="employeeProjectId")Long employeeProjectId){
        employeeProjectService.deleteById(employeeProjectId);
    }

    @PutMapping(value = "/{employeeProjectId}", produces = "application/json")
    public EmployeeProjectDTO updateEmployeeProject(@RequestBody EmployeeProjectDTO employeeProjectDTO,
                                          @PathVariable(name="employeeProjectId")Long employeeProjectId){
        return employeeProjectService.updateOne(employeeProjectId,employeeProjectDTO);
    }
}
