package com.rbc.h2.web;

import com.rbc.h2.persistence.dbo.Department;
import com.rbc.h2.service.department.DepartmentDTO;
import com.rbc.h2.service.department.DepartmentService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "{Department}")
@RequestMapping("department")
public class DepartmentResource {

    @Autowired
    DepartmentService departmentService;

    @GetMapping(value = "/{departmentId}", produces = "application/json")
    public DepartmentDTO getDepartment(@PathVariable(name="departmentId")Long departmentId) {
        return departmentService.readById(departmentId);
    }

    @GetMapping(value = "", produces = "application/json")
    public List<DepartmentDTO> getDepartments() {
        return departmentService.readAll();
    }

    @PostMapping(value = "", produces = "application/json")
    public DepartmentDTO saveDepartment(@RequestBody DepartmentDTO departmentDTO){
        return departmentService.addOne(departmentDTO);
    }

    @DeleteMapping(value = "/{departmentId}", produces = "application/json")
    public void deleteDepartment(@PathVariable(name="departmentId")Long departmentId){
        departmentService.deleteById(departmentId);
    }

    @PutMapping(value = "/{departmentId}", produces = "application/json")
    public DepartmentDTO updateDepartment(@RequestBody DepartmentDTO departmentDTO,
                               @PathVariable(name="departmentId")Long departmentId){
            return departmentService.updateOne(departmentId,departmentDTO);
    }
}
