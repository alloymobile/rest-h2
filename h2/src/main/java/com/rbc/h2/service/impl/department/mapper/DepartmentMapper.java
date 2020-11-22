package com.rbc.h2.service.impl.department.mapper;

import com.rbc.h2.persistence.dbo.Department;
import com.rbc.h2.service.impl.department.dto.DepartmentDTO;
import com.rbc.h2.service.mapper.H2Mapper;
import org.springframework.stereotype.Component;

@Component
public class DepartmentMapper extends H2Mapper<Department, DepartmentDTO> {

    @Override
    public void populateDBO(Department dbo, DepartmentDTO dto) {
        dbo.setName(dto.getName());
    }

    @Override
    public DepartmentDTO toDTOImpl(Department dbo) {
        DepartmentDTO dto = new DepartmentDTO();
        dto.setId(dbo.getId());
        dto.setName(dbo.getName());
        return dto;
    }
}
