package com.hiberus.hiring.application.query.impl;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import com.hiberus.hiring.domain.exception.InvalidBrandException;
import com.hiberus.hiring.domain.model.Brand;
import com.hiberus.hiring.domain.ports.out.BrandRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BrandQueryServiceImplTest {

  @Mock
  private BrandRepository brandRepository;

  @InjectMocks
  private BrandQueryServiceImpl brandQueryServiceImpl;

  private Long validBrandId;
  private Long invalidBrandId;

  @BeforeEach
  void setUp() {
    validBrandId = 1L;
    invalidBrandId = 2L;
  }

  @Test
  @DisplayName("Success: Given Brand Id When verify existing Brand, then no throws exception")
  void givenBrandIdWhenVerifyExistingBrandThenNoThrowsException() {
    when(brandRepository.findById(validBrandId)).thenReturn(Optional.of(new Brand()));
    brandQueryServiceImpl.verifyBrand(validBrandId);
  }

  @Test
  @DisplayName("Failure: Given Brand Id When verify existing Brand, then throws InvalidBrandException")
  void givenBrandIdWhenVerifyExistingBrandThenThrowsInvalidBrandException() {
    when(brandRepository.findById(invalidBrandId)).thenReturn(Optional.empty());
    assertThrows(InvalidBrandException.class,
        () -> brandQueryServiceImpl.verifyBrand(invalidBrandId));
  }
}
