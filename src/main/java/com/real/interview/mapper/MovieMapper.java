package com.real.interview.mapper;

import com.real.interview.entity.MovieEntity;
import com.real.interview.model.Movie;
import org.mapstruct.*;

@Mapper(uses = {CastMapper.class, AddressMapper.class, ReviewMapper.class},
        componentModel = MappingConstants.ComponentModel.SPRING,
        collectionMappingStrategy = CollectionMappingStrategy.SETTER_PREFERRED)
public interface MovieMapper {
    @Mapping(source = "reviewEntities", target = "reviews")
    @Mapping(source = "castEntities", target = "casts")
    Movie fromEntity (MovieEntity movieEntity);
    @InheritInverseConfiguration
    @Mapping(target = "version", ignore = true)
    MovieEntity toEntity (Movie movie);
}
