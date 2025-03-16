package com.hiberus.hiring.application.service;

import com.hiberus.hiring.domain.model.Brand;
import com.hiberus.hiring.domain.ports.in.BrandService;
import com.hiberus.hiring.domain.ports.out.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

  private final BrandRepository brandRepository;

  @Override
  public void create(Brand brand) {
    this.brandRepository.create(brand);
  }
}
