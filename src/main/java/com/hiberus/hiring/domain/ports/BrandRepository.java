package com.hiberus.hiring.domain.ports;

import com.hiberus.hiring.domain.model.Brand;
import java.util.Optional;

public interface BrandRepository {

  void create(Brand brand);

  Optional<Brand> findById(Long brandId);

}
