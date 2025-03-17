package com.hiberus.hiring.domain.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OfferProduct {

  @NotNull(message = "brandId is required")
  private Integer brandId;

  @NotNull(message = "productId is required")
  @Size(max = 12, message = "partnumber must not exceed 12 characters")
  private String partnumber;

}
