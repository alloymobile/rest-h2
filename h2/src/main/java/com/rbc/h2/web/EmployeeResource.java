package com.rbc.h2.web;

import com.rbc.h2.persistence.dbo.Employee;
import com.rbc.h2.service.employee.EmployeeDTO;
import com.rbc.h2.service.employee.EmployeeService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "{Employee}")
@RequestMapping("employee")
public class EmployeeResource {

    @Autowired
    EmployeeService employeeService;

    @GetMapping(value = "/{employeeId}", produces = "application/json")
    public EmployeeDTO getEmployee(@PathVariable(name="employeeId")Long employeeId) {
        return employeeService.readById(employeeId);
    }

    @GetMapping(value = "", produces = "application/json")
    public List<EmployeeDTO> getEmployees() {
        return employeeService.readAll();
    }

    @PostMapping(value = "", produces = "application/json")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO){
        return employeeService.addOne(employeeDTO);
    }

    @DeleteMapping(value = "/{employeeId}", produces = "application/json")
    public void deleteEmployee(@PathVariable(name="employeeId")Long employeeId){
        employeeService.deleteById(employeeId);
    }

    @PutMapping(value = "/{employeeId}", produces = "application/json")
    public EmployeeDTO updateEmployee(@RequestBody EmployeeDTO employeeDTO,
                                          @PathVariable(name="employeeId")Long employeeId){
        return employeeService.updateOne(employeeId,employeeDTO);
    }
}
