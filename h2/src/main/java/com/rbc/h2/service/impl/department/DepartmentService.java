package com.rbc.h2.service.impl.department;

import com.querydsl.core.types.Predicate;
import com.rbc.h2.exception.NotFoundException;
import com.rbc.h2.persistence.dbo.Department;
import com.rbc.h2.persistence.repository.DepartmentRepository;
import com.rbc.h2.service.impl.H2Service;
import com.rbc.h2.service.impl.department.dto.DepartmentDTO;
import com.rbc.h2.service.impl.department.mapper.DepartmentMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class  DepartmentService extends H2Service<Department, DepartmentDTO> {

    public DepartmentService(DepartmentMapper departmentMapper, DepartmentRepository departmentRepository) {
        super(departmentMapper, departmentRepository);
    }

    public Optional<ResponseEntity<DepartmentDTO>> readDepartmentById(Long id){
        return super.readById(id);
    }

    public Optional<ResponseEntity<Page<DepartmentDTO>>> readAllDepartment(Predicate predicate, Pageable pageable){
        return super.readAll(predicate,pageable);
    }

    public void deleteDepartmentById(Long id) {
        super.deleteById(id);
    }

    public Optional<ResponseEntity<DepartmentDTO>> createDepartment(DepartmentDTO newObject) {
        return super.createOne(this.h2Mapper.toNewDBO(newObject));
    }

    public Optional<ResponseEntity<DepartmentDTO>> updateDepartmentById(Long id, DepartmentDTO updatedObject) {
        return super.update(super.findById(id).orElseThrow(NotFoundException::new), updatedObject);
    }
}
