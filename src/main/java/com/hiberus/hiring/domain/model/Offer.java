package com.hiberus.hiring.domain.model;

import com.hiberus.hiring.utils.ValidationRegex;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Offer implements Serializable {

  @NotNull(message = "offerId is required")
  private Long offerId;

  @NotNull(message = "brandId is required")
  private Integer brandId;

  @NotNull(message = "startDate is required")
  @Pattern(regexp = ValidationRegex.UTC_DATE_TIME_REGEX, message = "startDate must be in the format YYYY-MM-DDTHH:MM:SSZ")
  private String startDate;

  @NotNull(message = "endDate is required")
  @Pattern(regexp = ValidationRegex.UTC_DATE_TIME_REGEX, message = "startDate must be in the format YYYY-MM-DDTHH:MM:SSZ")
  private String endDate;

  @NotNull(message = "priceListId is required")
  private Long priceListId;

  @NotNull(message = "productId is required")
  @Size(max = 12, message = "productPartnumber must not exceed 12 characters")
  private String productPartnumber;

  @NotNull(message = "priority is required")
  private Integer priority;

  @NotNull(message = "price is required")
  @DecimalMin(value = "0.01", inclusive = true, message = "price must be greater than 0")
  private BigDecimal price;

  @NotNull(message = "currencyIso is required")
  @Pattern(regexp = ValidationRegex.CURRENCY_REGEX, message = "currencyIso must be one of the following: USD, EUR")
  private String currencyIso;

}