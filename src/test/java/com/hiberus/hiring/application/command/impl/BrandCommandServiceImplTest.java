package com.hiberus.hiring.application.command.impl;

import static org.mockito.Mockito.verify;

import com.hiberus.hiring.domain.enums.BrandEnum;
import com.hiberus.hiring.domain.model.Brand;
import com.hiberus.hiring.domain.ports.BrandRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BrandCommandServiceImplTest {

  @Mock
  private BrandRepository brandRepository;

  @InjectMocks
  private BrandCommandServiceImpl brandCommandServiceImpl;

  private Brand brand;

  @BeforeEach
  void setUp() {
    brand = Brand.builder()
        .brandId(BrandEnum.HIBERUS.getId())
        .name(BrandEnum.HIBERUS.getName()).build();
  }

  @Test
  @DisplayName("Success: Given brand, When create is called, then save the brand")
  void givenBrandWhenCreateIsCalledThenSaveTheBrand() {
    brandCommandServiceImpl.create(brand);
    verify(brandRepository).create(brand);
  }
}