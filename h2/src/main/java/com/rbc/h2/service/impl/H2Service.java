package com.rbc.h2.service.impl;

import com.querydsl.core.types.Predicate;
import com.rbc.h2.persistence.IH2DBO;
import com.rbc.h2.persistence.repository.IH2JpaRepository;
import com.rbc.h2.service.H2RepositoryService;
import com.rbc.h2.service.dto.IH2DTO;
import com.rbc.h2.service.mapper.H2Mapper;
import com.rbc.h2.service.mapper.H2MapperPair;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.Optional;

@Getter
public abstract class H2Service<DBO_TYPE extends IH2DBO, DTO_TYPE extends IH2DTO> extends H2RepositoryService<DBO_TYPE> {

    protected final H2Mapper<DBO_TYPE,DTO_TYPE> h2Mapper;

    public H2Service(H2Mapper<DBO_TYPE,DTO_TYPE> h2Mapper, IH2JpaRepository<DBO_TYPE> iH2JpaRepository){
        super(iH2JpaRepository);
        this.h2Mapper = h2Mapper;
    }

    protected Optional<ResponseEntity<DTO_TYPE>> readById(Long id) {
        return super.findById(id).map(this.h2Mapper::toDTO);
    }


    protected Optional<ResponseEntity<Page<DTO_TYPE>>> readAll(Predicate predicate, Pageable pageable) {
        return super.findAll(predicate,pageable).map(this.h2Mapper::toDTOs);
    }

    protected Optional<ResponseEntity<DTO_TYPE>> createOne(DBO_TYPE dbo_type) {
        return super.create(dbo_type).map(this.h2Mapper::toDTO);
    }

    protected Optional<ResponseEntity<DTO_TYPE>> update(DBO_TYPE dboToUpdate, DTO_TYPE updatedObject) {
        if (dboToUpdate == null || updatedObject == null) {
            return Optional.empty();
        }
        this.h2Mapper.extendForUpdate(Collections.singletonList(new H2MapperPair<>(dboToUpdate, updatedObject)));
        return this.save(dboToUpdate).map(this.h2Mapper::toDTO);
    }

}
