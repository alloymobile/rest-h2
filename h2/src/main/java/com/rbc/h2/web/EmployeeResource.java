package com.rbc.h2.web;

import com.querydsl.core.types.Predicate;
import com.rbc.h2.exception.NotFoundException;
import com.rbc.h2.persistence.dbo.Employee;
import com.rbc.h2.persistence.repository.EmployeeRepository;
import com.rbc.h2.service.impl.employee.EmployeeService;
import com.rbc.h2.service.impl.employee.dto.EmployeeDTO;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "{Employee}")
@RequestMapping("employee")
public class EmployeeResource {

    private final EmployeeService employeeService;

    public EmployeeResource(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(value = "/{employeeId}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<EmployeeDTO> getEmployee(@PathVariable(name="employeeId")Long employeeId) {
        return employeeService.readEmployeeById(employeeId).orElseThrow(NotFoundException::new);
    }

    @GetMapping(value = "", produces = "application/json")
    public ResponseEntity<Page<EmployeeDTO>> getAllEmployees(
            @QuerydslPredicate(root = Employee.class,bindings = EmployeeRepository.class) Predicate predicate, Pageable pageable)
    {
        return employeeService.readAllEmployee(predicate, pageable).orElseThrow(NotFoundException::new);
    }

    @PostMapping(value = "", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<EmployeeDTO> saveEmployee(@RequestBody EmployeeDTO employeeDTO){
        return employeeService.createEmployee(employeeDTO).orElseThrow(NotFoundException::new);
    }

    @PutMapping(value = "/{employeeId}", produces = "application/json")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<EmployeeDTO> updateEmployee(@RequestBody EmployeeDTO employeeDTO,
                                                          @PathVariable(name="employeeId")Long employeeId){
        return employeeService.updateEmployeeById(employeeId, employeeDTO).orElseThrow(NotFoundException::new);
    }

    @DeleteMapping(value = "/{employeeId}", produces = "application/json")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Void> deleteEmployee(@PathVariable(name="employeeId")Long employeeId){
        employeeService.deleteEmployeeById(employeeId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
