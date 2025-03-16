package com.hiberus.hiring.application.service;

import static org.mockito.Mockito.verify;

import com.hiberus.hiring.domain.model.Brand;
import com.hiberus.hiring.domain.ports.out.BrandRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BrandServiceImplTest {

  @Mock
  private BrandRepository brandRepository;

  @InjectMocks
  private BrandServiceImpl brandService;

  private Brand brand;

  @BeforeEach
  void setUp() {
    brand = new Brand();
  }

  @Test
  @DisplayName("Success: Given a brand, when create, then persist the brand")
  void givenABrandWhenCreateThenPersistTheBrand() {
    brandService.create(brand);
    verify(brandRepository).create(brand);
  }
}
