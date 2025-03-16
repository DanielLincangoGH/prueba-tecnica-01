package com.hiberus.hiring.infrastructure.adapters.db;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "brand")
public class BrandEntity {

  @Id
  @Column(name = "brand_id")
  private Long brandId;

  @Column(name = "name", nullable = false, length = 32)
  private String name;

}

