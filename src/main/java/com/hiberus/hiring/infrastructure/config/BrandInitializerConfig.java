package com.hiberus.hiring.infrastructure.config;

import com.hiberus.hiring.application.command.BrandCommandService;
import com.hiberus.hiring.domain.enums.BrandEnum;
import com.hiberus.hiring.domain.model.Brand;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BrandInitializerConfig {

  private final BrandCommandService brandService;

  @PostConstruct()
  public void init() {
    brandService.create(Brand.builder()
        .brandId(BrandEnum.HIBERUS.getId())
        .name(BrandEnum.HIBERUS.getName()).build());
  }

}
