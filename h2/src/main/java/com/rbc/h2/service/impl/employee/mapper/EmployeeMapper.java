package com.rbc.h2.service.impl.employee.mapper;

import com.rbc.h2.exception.NotFoundException;
import com.rbc.h2.persistence.dbo.Employee;
import com.rbc.h2.service.impl.department.DepartmentService;
import com.rbc.h2.service.impl.employee.dto.EmployeeDTO;
import com.rbc.h2.service.mapper.H2Mapper;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper extends H2Mapper<Employee, EmployeeDTO> {

    private final DepartmentService departmentService;

    public EmployeeMapper(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @Override
    public void populateDBO(Employee dbo, EmployeeDTO dto) {
        if(dto.getDepartment() == null){
            throw new NotFoundException("Department can not be null");
        }
        dbo.setName(dto.getName());
        dbo.setEmail(dto.getEmail());
        dbo.setDepartment(this.departmentService.findById(dto.getDepartment().getId()).orElseThrow(NotFoundException::new));
    }

    @Override
    public EmployeeDTO toDTOImpl(Employee dbo) {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(dbo.getId());
        dto.setName(dbo.getName());
        dto.setEmail(dbo.getEmail());
        dto.setDepartment(this.departmentService.getH2Mapper().toDto(dbo.getDepartment()));
        return dto;
    }
}
