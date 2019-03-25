package com.rbc.h2.persistence.repository;

import com.rbc.h2.persistence.dbo.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Long> {
}
