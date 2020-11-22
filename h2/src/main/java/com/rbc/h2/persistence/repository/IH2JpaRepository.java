package com.rbc.h2.persistence.repository;

import com.rbc.h2.persistence.IH2DBO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface IH2JpaRepository <DBO_TYPE extends IH2DBO> extends JpaRepository<DBO_TYPE,Long>, QuerydslPredicateExecutor<DBO_TYPE> {
}
