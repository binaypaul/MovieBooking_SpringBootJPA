package com.real.interview.mapper;

import com.real.interview.entity.AddressEntity;
import com.real.interview.model.Address;
import org.mapstruct.*;

@Mapper(uses = {CastMapper.class, MovieMapper.class, ReviewMapper.class},
        componentModel = MappingConstants.ComponentModel.SPRING,
        collectionMappingStrategy = CollectionMappingStrategy.SETTER_PREFERRED)
public interface AddressMapper {
    @Mapping(source = "castEntity", target = "cast")
    Address fromEntity (AddressEntity addressEntity);
    @InheritInverseConfiguration
    @Mapping(target = "version", ignore = true)
    AddressEntity toEntity (Address address);
}
