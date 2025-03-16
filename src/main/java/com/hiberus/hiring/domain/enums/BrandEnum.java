package com.hiberus.hiring.domain.enums;

import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BrandEnum {

  HIBERUS(1L, "HIBERUS");

  private final Long id;
  private final String name;

  public static BrandEnum fromId(int id) {
    return Arrays.stream(BrandEnum.values())
        .filter(b -> b.getId() == id)
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException("Invalid brand ID: " + id));
  }
}
