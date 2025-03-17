package com.hiberus.hiring.infrastructure.adapters.mapper;

import com.hiberus.hiring.domain.enums.CurrencyEnum;
import com.hiberus.hiring.domain.model.Offer;
import com.hiberus.hiring.domain.model.OfferByPartNumber;
import com.hiberus.hiring.infrastructure.adapters.db.BrandEntity;
import com.hiberus.hiring.infrastructure.adapters.db.OfferEntity;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
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

  @Mapping(source = "endDate", target = "endDate", qualifiedByName = "offsetDateTimeToString")
  @Mapping(source = "startDate", target = "startDate", qualifiedByName = "offsetDateTimeToString")
  @Mapping(source = "brand", target = "brandId", qualifiedByName = "brandEntityToBrandId")
  @Mapping(source = "priceList", target = "priceListId", qualifiedByName = "bigDecimalToPriceListId")
  @Mapping(source = "currency", target = "currencyIso", qualifiedByName = "currencyToString")
  @Mapping(source = "partnumber", target = "productPartnumber")
  Offer toDomain(OfferEntity offerEntity);

  @Mapping(source = "startDate", target = "startDate", qualifiedByName = "offsetDateTimeToString")
  @Mapping(source = "endDate", target = "endDate", qualifiedByName = "offsetDateTimeToString")
  @Mapping(source = "price", target = "price")
  @Mapping(source = "currency", target = "currencyIso")
  OfferByPartNumber toProductOffers(OfferEntity offerEntity);

  List<Offer> toDomainList(List<OfferEntity> offerEntities);
  List<OfferByPartNumber> toProductOffersList(List<OfferEntity> offerEntity);

  @Named("stringToOffsetDateTime")
  default OffsetDateTime stringToOffsetDateTime(String value) {
    return OffsetDateTime.parse(value, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
  }

  @Named("offsetDateTimeToString")
  default String offsetDateTimeToString(OffsetDateTime value) {
    return value.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
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
  default CurrencyEnum stringToCurrency(String currencyIso) {
    return CurrencyEnum.valueOf(currencyIso);
  }

  @Named("padPartnumber")
  default String padPartnumber(String partnumber) {
    return String.format("%012d", Long.parseLong(partnumber));
  }

  @Named("brandEntityToBrandId")
  default Integer brandEntityToBrandId(BrandEntity brandEntity) {
    return brandEntity != null ? brandEntity.getBrandId().intValue() : null;
  }

  @Named("bigDecimalToPriceListId")
  default Long bigDecimalToPriceListId(BigDecimal priceList) {
    return priceList != null ? priceList.longValue() : null;
  }

  @Named("currencyToString")
  default String currencyToString(CurrencyEnum currency) {
    return currency.name();
  }

}
