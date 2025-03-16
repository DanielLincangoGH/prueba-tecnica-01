package com.hiberus.hiring.application.command.impl;

import com.hiberus.hiring.application.command.BrandCommandService;
import com.hiberus.hiring.domain.model.Brand;
import com.hiberus.hiring.domain.ports.out.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class BrandCommandServiceImpl implements BrandCommandService {

  private final BrandRepository brandRepository;

  @Override
  public void create(Brand brand) {
    this.brandRepository.create(brand);
  }

}
