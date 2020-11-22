package com.rbc.h2.persistence.repository;

import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;
import com.rbc.h2.persistence.dbo.Department;
import com.rbc.h2.persistence.dbo.QDepartment;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends IH2JpaRepository<Department> , QuerydslBinderCustomizer<QDepartment> {
    @Override
    default void customize(QuerydslBindings bindings, QDepartment department) {
        // Make case-insensitive 'like' filter for all string properties
        bindings.bind(String.class).first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);
    }
}
