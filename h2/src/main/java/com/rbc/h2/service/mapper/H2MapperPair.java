package com.rbc.h2.service.mapper;

import com.rbc.h2.persistence.IH2DBO;
import com.rbc.h2.service.dto.IH2DTO;

public class H2MapperPair<DBO_TYPE extends IH2DBO, DTO_TYPE extends IH2DTO> {
    public final DBO_TYPE dbo;
    public final DTO_TYPE dto;

    public H2MapperPair(DBO_TYPE dbo_type,DTO_TYPE dto_type) {
        this.dbo = dbo_type;
        this.dto = dto_type;
    }
}