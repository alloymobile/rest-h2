package com.rbc.h2.service.impl.employeeproject;

import com.rbc.h2.persistence.dbo.EmployeeProject;
import com.rbc.h2.persistence.repository.EmployeeProjectRepository;
import com.rbc.h2.service.H2RepositoryService;
import org.springframework.stereotype.Service;

@Service
public class EmployeeProjectService  extends H2RepositoryService<EmployeeProject> {

    public EmployeeProjectService(EmployeeProjectRepository employeeProjectRepository) {
        super(employeeProjectRepository);
    }
}
