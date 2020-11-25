package com.rbc.h2.service;

import com.querydsl.core.types.Predicate;
import com.rbc.h2.persistence.IH2DBO;
import com.rbc.h2.persistence.repository.IH2JpaRepository;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Getter
public abstract class H2RepositoryService<DBO_TYPE extends IH2DBO> {

    protected final IH2JpaRepository<DBO_TYPE> repository;

    public H2RepositoryService(IH2JpaRepository<DBO_TYPE> repository) {
        this.repository = repository;
    }

    //Find all the objects on the table
    protected Optional<Page<DBO_TYPE>> findAll(Predicate predicate, Pageable pageable) {
        if (null == pageable) {
            return Optional.empty();
        }
        final Page<DBO_TYPE> page;
        if (predicate == null) {
            page = this.getRepository().findAll(pageable);
        } else {
            page = this.getRepository().findAll(predicate, pageable);
        }
        return Optional.of(page);
    }

    //find an object by id and return dbo object need to join the objects
    public Optional<DBO_TYPE> findById(Long id){
        if(id == null){
            return Optional.empty();
        }
        return this.repository.findById(id);
    }

    //add one object and return dbo object
    public Optional<DBO_TYPE> create(DBO_TYPE dbo_type){
        if(dbo_type == null){
            return Optional.empty();
        }
        return Optional.of(this.repository.save(dbo_type));
    }

    //delete an object by ID
    public void deleteById(Long id) {
        if (id != null) {
            this.repository.findById(id).ifPresent(this::delete);
        }
    }

    //Method only used in this class
    public void delete(DBO_TYPE dbo_type) {
        if (dbo_type != null) {
            this.repository.delete(dbo_type);
        }
    }

    //Method only used in this class
    public void deleteAll(Iterable<DBO_TYPE>  iterable) {
        if (iterable != null) {
            this.repository.deleteAll(iterable);
        }
    }

    public Optional<List<DBO_TYPE>> createAll(Iterable<DBO_TYPE> iterable) {
        if (iterable == null || !iterable.iterator().hasNext()) {
            return Optional.empty();
        }
        return Optional.of(this.repository.saveAll(iterable));
    }
}
