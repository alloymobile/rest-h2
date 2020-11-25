package com.rbc.h2.service.impl.project;

import com.querydsl.core.types.Predicate;
import com.rbc.h2.exception.NotFoundException;
import com.rbc.h2.persistence.dbo.Employee;
import com.rbc.h2.persistence.dbo.EmployeeProject;
import com.rbc.h2.persistence.dbo.Project;
import com.rbc.h2.persistence.repository.EmployeeProjectRepository;
import com.rbc.h2.persistence.repository.ProjectRepository;
import com.rbc.h2.service.impl.H2Service;
import com.rbc.h2.service.impl.employee.EmployeeService;
import com.rbc.h2.service.impl.employee.dto.EmployeeDTO;
import com.rbc.h2.service.impl.employeeproject.EmployeeProjectService;
import com.rbc.h2.service.impl.project.dto.ProjectDTO;
import com.rbc.h2.service.impl.project.mapper.ProjectMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService extends H2Service<Project, ProjectDTO> {

    private final EmployeeService employeeService;
    private final EmployeeProjectService employeeProjectService;

    public ProjectService(ProjectMapper projectMapper
            , ProjectRepository projectRepository
            , EmployeeService employeeService
            , EmployeeProjectService employeeProjectService) {
        super(projectMapper, projectRepository);
        this.employeeService = employeeService;
        this.employeeProjectService = employeeProjectService;
    }

    public Optional<ResponseEntity<ProjectDTO>> readProjectById(Long id){
        return super.readById(id);
    }

    public Optional<ResponseEntity<Page<ProjectDTO>>> readAllProject(Predicate predicate, Pageable pageable){
        return super.readAll(predicate,pageable);
    }

    public void deleteProjectById(Long id) {
        super.deleteById(id);
    }

    //Used to create the project.
    public Optional<ResponseEntity<ProjectDTO>> createProject(ProjectDTO newObject) {
        if(newObject.getEmployees() == null){
            throw new NotFoundException("Project must have employees");
        }
        Optional<Project> project = this.create(this.getH2Mapper().toNewDBO(newObject));
        List<EmployeeProject> employeeProjectList = new ArrayList<>();
        if(project.isPresent()){
            for (EmployeeDTO e : newObject.getEmployees()) {
                EmployeeProject employeeProject = new EmployeeProject();
                employeeProject.setProject(project.get());
                Optional<Employee> employee = this.employeeService.findById(e.getId());
                if(employee.isPresent()) {
                    employeeProject.setEmployee(employee.get());
                    Optional<EmployeeProject> employeeProjectOptional = this.employeeProjectService.create(employeeProject);
                    employeeProjectOptional.ifPresent(emp->employeeProjectList.add(emp));
                }else{
                    throw new NotFoundException("Employee not found");
                }
            }
            project.get().setProjectEmployeeList(employeeProjectList);
            return Optional.of(this.h2Mapper.toDTO(project.get()));
        }else{
            throw new NotFoundException("Cannot create project");
        }
    }

    public Optional<ResponseEntity<ProjectDTO>> updateProjectById(Long id, ProjectDTO updatedObject) {
        return super.update(super.findById(id).orElseThrow(NotFoundException::new), updatedObject);
    }
}
