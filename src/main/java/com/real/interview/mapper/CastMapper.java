package com.real.interview.mapper;

import com.real.interview.entity.CastEntity;
import com.real.interview.model.Cast;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        collectionMappingStrategy = CollectionMappingStrategy.SETTER_PREFERRED)
public interface CastMapper {
    @Mapping(source = "addressEntity", target = "address")
    @Mapping(source = "movieEntities", target = "movies")
    Cast fromEntity (CastEntity castEntity);
    @InheritInverseConfiguration
//    @Mapping(target = "version", ignore = true)
    CastEntity toEntity (Cast person);
}
