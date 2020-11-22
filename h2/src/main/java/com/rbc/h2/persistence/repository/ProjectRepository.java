package com.rbc.h2.persistence.repository;

import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;
import com.rbc.h2.persistence.dbo.Project;
import com.rbc.h2.persistence.dbo.QProject;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends IH2JpaRepository<Project> , QuerydslBinderCustomizer<QProject> {
    @Override
    default void customize(QuerydslBindings bindings, QProject project) {
        // Make case-insensitive 'like' filter for all string properties
        bindings.bind(String.class).first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);
    }
}
