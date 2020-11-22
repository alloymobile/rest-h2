package com.rbc.h2.service.impl.employee;

import com.querydsl.core.types.Predicate;
import com.rbc.h2.exception.NotFoundException;
import com.rbc.h2.persistence.dbo.Employee;
import com.rbc.h2.persistence.repository.EmployeeRepository;
import com.rbc.h2.service.impl.H2Service;
import com.rbc.h2.service.impl.employee.dto.EmployeeDTO;
import com.rbc.h2.service.impl.employee.mapper.EmployeeMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService extends H2Service<Employee, EmployeeDTO> {

    public EmployeeService(EmployeeMapper employeeMapper, EmployeeRepository employeeRepository) {
        super(employeeMapper, employeeRepository);
    }

    public Optional<ResponseEntity<EmployeeDTO>> readEmployeeById(Long id){
        return super.readById(id);
    }

    public Optional<ResponseEntity<Page<EmployeeDTO>>> readAllEmployee(Predicate predicate, Pageable pageable){
        return super.readAll(predicate,pageable);
    }

    public void deleteEmployeeById(Long id) {
        super.deleteById(id);
    }

    public Optional<ResponseEntity<EmployeeDTO>> createEmployee(EmployeeDTO newObject) {
        return super.createOne(this.h2Mapper.toNewDBO(newObject));
    }

    public Optional<ResponseEntity<EmployeeDTO>> updateEmployeeById(Long id, EmployeeDTO updatedObject) {
        return super.update(super.findById(id).orElseThrow(NotFoundException::new), updatedObject);
    }
}
