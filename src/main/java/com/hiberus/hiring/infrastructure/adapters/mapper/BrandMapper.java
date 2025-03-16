package com.hiberus.hiring.infrastructure.adapters.mapper;

import com.hiberus.hiring.domain.model.Brand;
import com.hiberus.hiring.infrastructure.adapters.db.BrandEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BrandMapper {

  BrandEntity toEntity(Brand brand);

}
