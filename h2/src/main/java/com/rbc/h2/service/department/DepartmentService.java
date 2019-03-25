package com.rbc.h2.service.department;

import com.rbc.h2.persistence.dbo.Department;
import com.rbc.h2.persistence.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;

    //read all
    public List<DepartmentDTO> readAll(){
        List<DepartmentDTO> departmentDTOList = new ArrayList<>();
        List<Department> department = departmentRepository.findAll();
        for(Department d: department){
            departmentDTOList.add(this.toDTO(d));
        }
        return departmentDTOList;
    }

    //read one
    public DepartmentDTO readById(Long id){
        return this.toDTO(departmentRepository.findById(id).get());
    }

    //addOne
    public DepartmentDTO addOne(DepartmentDTO departmentDTO){
        return this.toDTO(departmentRepository.save(this.toDBO(departmentDTO)));
    }

    //update or add one
    public DepartmentDTO updateOne(Long id,DepartmentDTO departmentDTO){
        if(departmentRepository.findById(id).isPresent()){
            departmentDTO.setId(departmentDTO.getId());
        }
        return this.addOne(departmentDTO);
    }

    //delete by id
    public void deleteById(Long id){
        this.departmentRepository.deleteById(id);
    }

    public DepartmentDTO toDTO(Department department){
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setId(department.getId());
        departmentDTO.setName(department.getName());
        return departmentDTO;
    }

    public Department toDBO(DepartmentDTO departmentDTO){
        Department department = new Department();
        if(departmentDTO.getId() != 0){
            department.setId(departmentDTO.getId());
        }
        department.setName(departmentDTO.getName());
        return department;
    }
}
