package com.rbc.h2.service.project;

import com.rbc.h2.persistence.dbo.Project;
import com.rbc.h2.persistence.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectService {

    @Autowired
    ProjectRepository projectRepository;

    //read all
    public List<ProjectDTO> readAll(){
        List<ProjectDTO> projectDTOList = new ArrayList<>();
        List<Project> project = projectRepository.findAll();
        for(Project d: project){
            projectDTOList.add(this.toDTO(d));
        }
        return projectDTOList;
    }

    //read one
    public ProjectDTO readById(Long id){
        return this.toDTO(projectRepository.findById(id).get());
    }

    //addOne
    public ProjectDTO addOne(ProjectDTO projectDTO){
        return this.toDTO(projectRepository.save(this.toDBO(projectDTO)));
    }

    //update or add one
    public ProjectDTO updateOne(Long id,ProjectDTO projectDTO){
        if(this.readById(id) != null){
            projectDTO.setId(id);
        }
        return this.addOne(projectDTO);
    }

    //delete by id
    public void deleteById(Long id){
        this.projectRepository.deleteById(id);
    }

    public ProjectDTO toDTO(Project project){
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setId(project.getId());
        projectDTO.setName(project.getName());
        return projectDTO;
    }

    public Project toDBO(ProjectDTO projectDTO){
        Project project = new Project();
        if(projectDTO.getId() != 0){
            project.setId(projectDTO.getId());
        }
        project.setName(projectDTO.getName());
        return project;
    }
}
