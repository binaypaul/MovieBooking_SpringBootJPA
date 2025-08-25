package com.real.interview.mapper;

import com.real.interview.entity.ReviewEntity;
import com.real.interview.model.Review;
import org.mapstruct.*;

@Mapper(uses = {CastMapper.class, AddressMapper.class, MovieMapper.class},
        componentModel = MappingConstants.ComponentModel.SPRING,
        collectionMappingStrategy = CollectionMappingStrategy.SETTER_PREFERRED)
public interface ReviewMapper {
    @Mapping(source = "movieEntity", target = "movie")
    Review fromEntity (ReviewEntity reviewEntity);
    @InheritInverseConfiguration
    @Mapping(target = "version", ignore = true)
    ReviewEntity toEntity (Review review);
}
