package com.hiberus.hiring.infrastructure.condig;

import com.hiberus.hiring.domain.enums.BrandEnum;
import com.hiberus.hiring.domain.model.Brand;
import com.hiberus.hiring.domain.ports.in.BrandService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BrandInitializerConfig {

  private final BrandService brandService;

  @PostConstruct()
  public void init() {
    brandService.create(Brand.builder()
        .brandId(BrandEnum.HIBERUS.getId())
        .name(BrandEnum.HIBERUS.getName()).build());
  }

}
