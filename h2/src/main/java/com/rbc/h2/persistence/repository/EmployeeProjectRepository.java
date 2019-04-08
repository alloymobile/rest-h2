package com.rbc.h2.persistence.repository;

import com.rbc.h2.persistence.dbo.EmployeeProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeProjectRepository extends JpaRepository<EmployeeProject,Long> {
}
