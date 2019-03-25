package com.rbc.h2.service.employee;

import com.rbc.h2.persistence.dbo.Employee;
import com.rbc.h2.persistence.repository.EmployeeRepository;
import com.rbc.h2.service.department.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    DepartmentService departmentService;

    //read all
    public List<EmployeeDTO> readAll(){
        List<EmployeeDTO> employeeDTOList = new ArrayList<>();
        List<Employee> employee = employeeRepository.findAll();
        for(Employee d: employee){
            employeeDTOList.add(this.toDTO(d));
        }
        return employeeDTOList;
    }

    //read one
    public EmployeeDTO readById(Long id){
        return this.toDTO(employeeRepository.findById(id).get());
    }

    //addOne
    public EmployeeDTO addOne(EmployeeDTO employeeDTO){
        return this.toDTO(employeeRepository.save(this.toDBO(employeeDTO)));
    }

    //update or add one
    public EmployeeDTO updateOne(Long id,EmployeeDTO employeeDTO){
        if(this.readById(id) != null){
            employeeDTO.setId(id);
        }
        return this.addOne(employeeDTO);
    }

    //delete by id
    public void deleteById(Long id){
        this.employeeRepository.deleteById(id);
    }

    public EmployeeDTO toDTO(Employee employee){
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employee.getId());
        employeeDTO.setName(employee.getName());
        employeeDTO.setEmail(employee.getEmail());
        employeeDTO.setDepartmentId(employee.getDepartment().getId());
        return employeeDTO;
    }

    public Employee toDBO(EmployeeDTO employeeDTO){
        Employee employee = new Employee();
        if(employeeDTO.getId() != 0){
            employee.setId(employeeDTO.getId());
        }
        employee.setName(employeeDTO.getName());
        employee.setEmail(employeeDTO.getEmail());
        employee.setDepartment(departmentService.toDBO(departmentService.readById(employeeDTO.getDepartmentId())));
        return employee;
    }
}
