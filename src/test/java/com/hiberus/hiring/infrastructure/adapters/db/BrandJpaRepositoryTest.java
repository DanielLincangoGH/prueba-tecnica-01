package com.hiberus.hiring.infrastructure.adapters.db;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.hiberus.hiring.domain.model.Brand;
import com.hiberus.hiring.infrastructure.adapters.mapper.BrandMapper;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BrandJpaRepositoryTest {

  @Mock
  private EntityManager entityManager;

  @Mock
  private BrandMapper brandMapper;

  @InjectMocks
  private BrandJpaRepository brandJpaRepository;

  private Brand brand;
  private BrandEntity brandEntity;

  @BeforeEach
  void setUp() {
    brand = new Brand();
    brandEntity = new BrandEntity();
  }

  @Test
  @DisplayName("Success: Given a brand, when create, then persist the brand entity")
  void givenABrandWhenCreateThenPersistTheBrandEntity() {
    when(brandMapper.toEntity(brand)).thenReturn(brandEntity);
    brandJpaRepository.create(brand);
    verify(entityManager).merge(brandEntity);
  }
}
