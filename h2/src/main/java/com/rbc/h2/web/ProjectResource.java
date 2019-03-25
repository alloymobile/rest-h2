package com.rbc.h2.web;

import com.rbc.h2.persistence.dbo.Project;
import com.rbc.h2.service.project.ProjectDTO;
import com.rbc.h2.service.project.ProjectService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "{Project}")
@RequestMapping("project")
public class ProjectResource {

    @Autowired
    ProjectService projectService;

    @GetMapping(value = "/{projectId}", produces = "application/json")
    public ProjectDTO getProject(@PathVariable(name="projectId")Long projectId) {
        return projectService.readById(projectId);
    }

    @GetMapping(value = "", produces = "application/json")
    public List<ProjectDTO> getProjects() {
        return projectService.readAll();
    }

    @PostMapping(value = "", produces = "application/json")
    public ProjectDTO saveProject(@RequestBody ProjectDTO projectDTO){
        return projectService.addOne(projectDTO);
    }

    @DeleteMapping(value = "/{projectId}", produces = "application/json")
    public void deleteProject(@PathVariable(name="projectId")Long projectId){
        projectService.deleteById(projectId);
    }

    @PutMapping(value = "/{projectId}", produces = "application/json")
    public ProjectDTO updateProject(@RequestBody ProjectDTO projectDTO,
                                          @PathVariable(name="projectId")Long projectId){
        return projectService.updateOne(projectId,projectDTO);
    }
}
