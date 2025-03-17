package com.hiberus.hiring.infrastructure.config;

import static org.mockito.Mockito.verify;

import com.hiberus.hiring.application.command.BrandCommandService;
import com.hiberus.hiring.domain.enums.BrandEnum;
import com.hiberus.hiring.domain.model.Brand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BrandInitializerConfigTest {

  @Mock
  private BrandCommandService brandService;

  @InjectMocks
  private BrandInitializerConfig brandInitializerConfig;

  private Brand brand;

  @BeforeEach
  void setUp() {
    brand = Brand.builder()
        .brandId(BrandEnum.HIBERUS.getId())
        .name(BrandEnum.HIBERUS.getName())
        .build();
  }

  @Test
  @DisplayName("Success: Given a brand, when init is called, then create the brand")
  void givenABrandWhenInitIsCalledThenCreateTheBrand() {
    brandInitializerConfig.init();
    verify(brandService).create(brand);
  }
}