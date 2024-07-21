package com.techblog.blogtech.mappers;

import com.techblog.blogtech.domain.MainEntity;
import org.mapstruct.MapperConfig;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@MapperConfig
public interface MainMapper<D, M extends MainEntity> {

    D toDto(M entity);

    M toEntity(D dto);

    @Mapping(target = "id", ignore = true)
    M toEntity(@MappingTarget M entity, D dto);
}
