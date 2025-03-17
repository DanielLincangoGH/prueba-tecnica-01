package com.hiberus.hiring.application.query.impl;

import com.hiberus.hiring.application.query.BrandQueryService;
import com.hiberus.hiring.domain.exception.InvalidBrandException;
import com.hiberus.hiring.domain.ports.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BrandQueryServiceImpl implements BrandQueryService {

  private final BrandRepository brandRepository;

  @Override
  public void verifyBrand(Long brandId) {
    if (brandRepository.findById(brandId).isEmpty()) {
      throw new InvalidBrandException("Brand is invalid");
    }
  }
}
