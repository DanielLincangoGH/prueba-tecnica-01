package com.hiberus.hiring.infrastructure.adapters.db;

import com.hiberus.hiring.domain.model.Brand;
import com.hiberus.hiring.domain.ports.out.BrandRepository;
import com.hiberus.hiring.infrastructure.adapters.mapper.BrandMapper;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BrandJpaRepository implements BrandRepository {

  private final EntityManager entityManager;
  private final BrandMapper brandMapper;

  @Override
  public void create(Brand brand) {
    entityManager.merge(brandMapper.toEntity(brand));
  }
}
