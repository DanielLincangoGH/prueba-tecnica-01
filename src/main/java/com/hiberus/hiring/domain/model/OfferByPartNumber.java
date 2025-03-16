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
public class OfferByPartNumber implements Serializable {

    private String startDate;
    private String endDate;
    private BigDecimal price;
    private String currencyIso;

}