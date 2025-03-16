package com.hiberus.hiring.infraestructure.mapper;

import com.hiberus.hiring.domain.model.Currency;
import com.hiberus.hiring.domain.model.Offer;
import com.hiberus.hiring.infraestructure.persistence.BrandEntity;
import com.hiberus.hiring.infraestructure.persistence.OfferEntity;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface OfferMapper {

  @Mapping(source = "endDate", target = "endDate", qualifiedByName = "stringToOffsetDateTime")
  @Mapping(source = "startDate", target = "startDate", qualifiedByName = "stringToOffsetDateTime")
  @Mapping(source = "brandId", target = "brand", qualifiedByName = "brandIdToBrandEntity")
  @Mapping(source = "priceListId", target = "priceList", qualifiedByName = "priceListIdToBigDecimal")
  @Mapping(source = "currencyIso", target = "currency", qualifiedByName = "stringToCurrency")
  @Mapping(source = "productPartnumber", target = "partnumber", qualifiedByName = "padPartnumber")
  OfferEntity toOfferEntity(Offer offer);

  @Named("stringToOffsetDateTime")
  default OffsetDateTime stringToOffsetDateTime(String value) {
    return OffsetDateTime.parse(value, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
  }

  @Named("brandIdToBrandEntity")
  default BrandEntity brandIdToBrandEntity(Integer brandId) {
    if (brandId == null) {
      return null;
    }
    BrandEntity brandEntity = new BrandEntity();
    brandEntity.setBrandId(brandId.longValue());
    return brandEntity;
  }

  @Named("priceListIdToBigDecimal")
  default BigDecimal priceListIdToBigDecimal(Long priceListId) {
    return priceListId != null ? BigDecimal.valueOf(priceListId) : null;
  }

  @Named("stringToCurrency")
  default Currency stringToCurrency(String currencyIso) {
    return Currency.valueOf(currencyIso);
  }

  @Named("padPartnumber")
  default String padPartnumber(String partnumber) {
    return String.format("%012d", Long.parseLong(partnumber));
  }

}
