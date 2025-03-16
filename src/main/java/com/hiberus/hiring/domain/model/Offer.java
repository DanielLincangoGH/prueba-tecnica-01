package com.hiberus.hiring.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Offer implements Serializable {

    private Long offerId;
    private Integer brandId;
    private String startDate;
    private String endDate;
    private Long priceListId;
    private String productPartnumber;
    private Integer priority;
    private BigDecimal price;
    private String currencyIso;


}