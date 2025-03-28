package com.hiberus.hiring.infrastructure.adapters.db;

import com.hiberus.hiring.domain.enums.CurrencyEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "offers")
public class OfferEntity {

  @Id
  @Column(name = "offer_id")
  private Long offerId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "brand_id", referencedColumnName = "brand_id", nullable = false)
  private BrandEntity brand;

  @Column(name = "start_date", nullable = false)
  private OffsetDateTime startDate;

  @Column(name = "end_date", nullable = false)
  private OffsetDateTime endDate;

  @Column(name = "price_list", nullable = false)
  private BigDecimal priceList;

  @Column(name = "partnumber", nullable = false, length = 12)
  private String partnumber;

  @Column(name = "priority", nullable = false)
  private Short priority;

  @Column(name = "price", nullable = false)
  private BigDecimal price;

  @Enumerated(EnumType.STRING)
  @Column(name = "curr", nullable = false)
  private CurrencyEnum currency;

}
