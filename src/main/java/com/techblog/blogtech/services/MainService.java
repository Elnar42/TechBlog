package com.techblog.blogtech.services;

import com.techblog.blogtech.domain.MainEntity;
import com.techblog.blogtech.exceptions.NoDataFound;
import com.techblog.blogtech.mappers.MainMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public abstract class MainService<D, ID extends Serializable, O extends MainEntity> {

    private final JpaRepositoryImplementation<O, ID> repository;
    private final MainMapper<D, O> mapper;

    public Page<D> getAll(Pageable pageable) {
        Page<O> all = repository.findAll(pageable);
        return all.map(mapper::toDto);
    }

    public D getById(ID id) {
        return repository
                .findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new NoDataFound("No record found with id: " + id, LocalDateTime.now()));
    }

    public D create(D element) {
        return mapper.toDto(repository.save(mapper.toEntity(element)));
    }

    public D updateById(ID id, D element) {
        return repository
                .findById(id)
                .map(object -> repository.save(mapper.toEntity(object, element)))
                .map(mapper::toDto)
                .orElseThrow(() -> new NoDataFound("No record found with id: " + id, LocalDateTime.now()));
    }


    public void deleteById(ID id) {
        repository.delete(repository.findById(id).orElseThrow(() -> new NoDataFound("No record found with id: " + id, LocalDateTime.now())));
    }


}
