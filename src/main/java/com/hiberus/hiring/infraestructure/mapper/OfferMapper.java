package com.hiberus.hiring.infraestructure.mapper;

import com.hiberus.hiring.domain.model.Offer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OfferMapper {

  OfferMapper INSTANCE = Mappers.getMapper(OfferMapper.class);

  Offer toOfferEntity(Offer offer);

}
