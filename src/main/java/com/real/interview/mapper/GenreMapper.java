package com.real.interview.mapper;

import com.real.interview.entity.GenreEntity;
import com.real.interview.model.Genre;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        collectionMappingStrategy = CollectionMappingStrategy.SETTER_PREFERRED)
public interface GenreMapper {
    @Mapping(source = "movieEntities", target = "movies")
    Genre fromEntity (GenreEntity genreEntity);
    @InheritInverseConfiguration
//    @Mapping(target = "version", ignore = true)
    GenreEntity toEntity (Genre genre);
}
