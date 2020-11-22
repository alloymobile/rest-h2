package com.rbc.h2.service.impl.department.dto;

import com.rbc.h2.service.dto.IH2DTO;
import lombok.Data;

@Data
public class DepartmentDTO implements IH2DTO {
    private Long id;
    private String name;
}
