package com.real.interview.mapper;

import com.real.interview.entity.MovieEntity;
import com.real.interview.model.Movie;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        collectionMappingStrategy = CollectionMappingStrategy.SETTER_PREFERRED)
public interface MovieMapper {
    @Mapping(source = "genreEntity", target = "genre")
    @Mapping(source = "castEntities", target = "casts")
    Movie fromEntity (MovieEntity movieEntity);
    @InheritInverseConfiguration
    @Mapping(target = "version", ignore = true)
    MovieEntity toEntity (Movie movie);
}
